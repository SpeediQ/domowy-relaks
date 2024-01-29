package pl.domowyrelaks.domowyrelaks.utils;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import org.jetbrains.annotations.NotNull;
import pl.domowyrelaks.domowyrelaks.dto.EventDTO;

import java.util.Arrays;

public class CalendarUtils {
    public static final String EMAIL_METHOD = "email";
    public static final String POPUP_METHOD = "popup";
    public static final String EUROPE_WARSAW = "Europe/Warsaw";
    public static final String NULL_HTTP_TRANSPORT_MESSAGE = "HTTP_TRANSPORT can't be null";

    @NotNull
    public static EventDateTime getEventDateTime(DateTime startDateTime) {
        return new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone(EUROPE_WARSAW);
    }

    @NotNull
    public static Event getEventByEventDTO(EventDTO eventDTO) {
        Event event = new Event()
                .setSummary(eventDTO.getSummary())
                .setLocation(eventDTO.getLocation())
                .setDescription(eventDTO.getDescription())
                .setStart(eventDTO.getStart())
                .setEnd(eventDTO.getEnd())
                .setReminders(eventDTO.getReminders());
        return event;
    }

    @NotNull
    public static Event.Reminders prepareReminders() {
        EventReminder[] reminderOverrides = new EventReminder[]{
                new EventReminder().setMethod(EMAIL_METHOD).setMinutes(24 * 60),
                new EventReminder().setMethod(POPUP_METHOD).setMinutes(10),
        };
        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        return reminders;
    }

    @NotNull
    public static DateTime mapDateTimeFromString(String string) {
        return new DateTime(string);
    }

    @NotNull
    public static EventDateTime mapEventDateTimeFromString(String string) {
        DateTime dateTime = mapDateTimeFromString(string);
        return getEventDateTime(dateTime);
    }
}
