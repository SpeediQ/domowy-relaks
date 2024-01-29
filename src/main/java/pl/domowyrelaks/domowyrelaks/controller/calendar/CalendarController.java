package pl.domowyrelaks.domowyrelaks.controller.calendar;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import pl.domowyrelaks.domowyrelaks.dto.EventDTO;
import pl.domowyrelaks.domowyrelaks.model.Visit;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import static pl.domowyrelaks.domowyrelaks.utils.CalendarUtils.*;
import static pl.domowyrelaks.domowyrelaks.utils.ProjectUtils.*;

@Controller
public class CalendarController {
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES =
            Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    public static final String PRIMARY_CALENDAR_ID = "primary";
    public static final String OFFLINE_ACCESS_TYPE = "offline";
    public static final String LOCATION = "CITY_ABC";
    public static final String PREPARATION_TEXT = "Przygotowanie: ";

    public static Credential getCredentials(final NetHttpTransport httpTransport)
            throws IOException {
        if (httpTransport == null) {
            throw new NullPointerException(NULL_HTTP_TRANSPORT_MESSAGE);
        }
        InputStream in = CalendarController.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType(OFFLINE_ACCESS_TYPE)
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        return credential;

    }

    public static void startCalendar() throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        Calendar service = prepareCalendarService();

        // List the next 10 events from the primary calendar.
        Events events = getEventList(service, new DateTime(System.currentTimeMillis()), 10);

        printEvents(events);

    }

    private static void printEvents(Events events) {
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                System.out.printf("%s (%s)\n", event.getSummary(), start);
                System.out.println(event.getId());
                System.out.println("----------------");
            }
        }
    }

    private static Events getEventList(Calendar service, DateTime now, int maxResults) throws IOException {
        return service.events().list(PRIMARY_CALENDAR_ID)
                .setMaxResults(maxResults)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
    }

    @NotNull
    public static Calendar prepareCalendarService() throws IOException, GeneralSecurityException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        return new Calendar.Builder(httpTransport, JSON_FACTORY, getCredentials(httpTransport))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static String createAndInsertEvent(Calendar service, EventDTO eventDTO) throws IOException {


        Event event = getEventByEventDTO(eventDTO);
        event = insertEvent(service, event);

        System.out.printf("Event created: %s\n", event.getHtmlLink());
        return event.getId();
    }

    @NotNull
    public static EventDTO prepareEventDTO() {
        EventDTO eventDTO = new EventDTO("MÓJ WIELKI TEST", "MaaaP", "TEST DESC - WIELKI TEST");

        handleEventStartEndDateTime(eventDTO, mapDateTimeFromString("2025-02-01T15:00:00+01:00"),
                mapDateTimeFromString("2025-02-01T18:00:00+01:00"));

        Event.Reminders reminders = prepareReminders();
        eventDTO.setReminders(reminders);
        return eventDTO;
    }

    @NotNull
    public static EventDTO prepareEventDTOFromVisit(Visit visit) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setSummary(visit.getSummary());
        eventDTO.setDescription("ID: " + visit.getId() + " --- " + visit.getDesc());
        eventDTO.setLocation(LOCATION);
        eventDTO.setStart(mapEventDateTimeFromString(visit.getStart()));
        eventDTO.setEnd(mapEventDateTimeFromString(visit.getEnd()));
        Event.Reminders reminders = prepareReminders();
        eventDTO.setReminders(reminders);
        return eventDTO;
    }

    @NotNull
    public static EventDTO preparePreparationEventDTOFromVisit(Visit visit) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setSummary(PREPARATION_TEXT + visit.getSummary());
        eventDTO.setDescription("ID: " + visit.getId() + " --- " + visit.getDesc());
        eventDTO.setLocation(LOCATION);
        eventDTO.setStart(mapEventDateTimeFromString(getEarlierDateTime(visit.getStart(), visit.getPreparationTime())));
        eventDTO.setEnd(mapEventDateTimeFromString(visit.getStart()));
        Event.Reminders reminders = prepareReminders();
        eventDTO.setReminders(reminders);
        return eventDTO;
    }


    public static void insertCalendarEventsByVisit(Visit visit) throws IOException, GeneralSecurityException {
        EventDTO eventDTO = prepareEventDTOFromVisit(visit);
        EventDTO preparationEventDTO = preparePreparationEventDTOFromVisit(visit);
        Calendar calendar = prepareCalendarService();
        createAndInsertEvent(calendar, eventDTO);
        createAndInsertEvent(calendar, preparationEventDTO);
    }

    private static Event insertEvent(Calendar service, Event event) throws IOException {
        return service.events().insert(PRIMARY_CALENDAR_ID, event).execute();
    }


    private static void handleEventStartEndDateTime(EventDTO eventDTO, DateTime startDateTime, DateTime endDateTime) {
        EventDateTime start = getEventDateTime(startDateTime);

        EventDateTime end = getEventDateTime(endDateTime);
        setStartEnd(eventDTO, start, end);
    }

    private static void setStartEnd(EventDTO eventDTO, EventDateTime start, EventDateTime end) {
        eventDTO.setStart(start);
        eventDTO.setEnd(end);
    }


}

