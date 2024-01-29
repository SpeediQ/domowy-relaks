package pl.domowyrelaks.domowyrelaks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.domowyrelaks.domowyrelaks.controller.VisitController;
import pl.domowyrelaks.domowyrelaks.model.Visit;
import pl.domowyrelaks.domowyrelaks.repository.ClientRepository;
import pl.domowyrelaks.domowyrelaks.repository.VisitRepository;
import pl.domowyrelaks.domowyrelaks.service.VisitService;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static pl.domowyrelaks.domowyrelaks.controller.calendar.CalendarController.insertCalendarEventsByVisit;

@SpringBootApplication
public class DomowyRelaksApplication {

    private static VisitService visitService;

    public DomowyRelaksApplication(VisitService visitService) {
        this.visitService = visitService;
    }

    public static void main(String[] args) {
        SpringApplication.run(DomowyRelaksApplication.class, args);
        try {
            visitService.syncWithCloud();

//            Visit visit = new Visit();
//            visit.setSummary("Test - MÓJ WIELKI TEST");
//            visit.setDesc("Test Desc - WIELKI TEST");
//            visit.setStart("2025-02-01T15:00:00+01:00");
//            visit.setEnd("2025-02-01T18:30:00+01:00");
//            visit.setPreparationTime(30);
//            insertCalendarEventsByVisit(visit);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

}
