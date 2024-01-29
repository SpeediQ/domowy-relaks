package pl.domowyrelaks.domowyrelaks;

import com.google.api.services.calendar.Calendar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.domowyrelaks.domowyrelaks.calendar.CalendarQuickstart;
import pl.domowyrelaks.domowyrelaks.dto.EventDTO;
import pl.domowyrelaks.domowyrelaks.model.Visit;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static pl.domowyrelaks.domowyrelaks.calendar.CalendarQuickstart.*;
import static pl.domowyrelaks.domowyrelaks.utils.CalendarUtils.getEventByEventDTO;

@SpringBootApplication
public class DomowyRelaksApplication {

    public static void main(String[] args) {
        SpringApplication.run(DomowyRelaksApplication.class, args);

        try {

            Visit visit = new Visit();
            visit.setSummary("Test - MÓJ WIELKI TEST");
            visit.setDesc("Test Desc - WIELKI TEST");
            visit.setStart("2025-02-01T15:00:00+01:00");
            visit.setEnd("2025-02-01T18:30:00+01:00");
            visit.setPreparationTime(30);

            insertEventsByVisit(visit);
//            CalendarQuickstart.startCalendar();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

}
