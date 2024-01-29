package pl.domowyrelaks.domowyrelaks.dto;

import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

public class EventDTO {
    private String summary;
    private String location;
    private String description;
    private EventDateTime start;
    private EventDateTime end;
    private Event.Reminders reminders;

    public EventDTO() {
    }

    public EventDTO(String summary, String location, String description) {
        this.summary = summary;
        this.location = location;
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventDateTime getStart() {
        return start;
    }

    public void setStart(EventDateTime start) {
        this.start = start;
    }

    public EventDateTime getEnd() {
        return end;
    }

    public void setEnd(EventDateTime end) {
        this.end = end;
    }

    public Event.Reminders getReminders() {
        return reminders;
    }

    public void setReminders(Event.Reminders reminders) {
        this.reminders = reminders;
    }
}
