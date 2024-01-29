package pl.domowyrelaks.domowyrelaks.utils;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import org.jetbrains.annotations.NotNull;
import pl.domowyrelaks.domowyrelaks.dto.EventDTO;

import static pl.domowyrelaks.domowyrelaks.calendar.CalendarQuickstart.EUROPE_WARSAW;

public class CalendarUtils {
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
}
