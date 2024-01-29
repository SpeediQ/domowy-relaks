package pl.domowyrelaks.domowyrelaks.controller;

import org.springframework.stereotype.Controller;
import pl.domowyrelaks.domowyrelaks.model.Visit;
import pl.domowyrelaks.domowyrelaks.repository.VisitRepository;

@Controller
public class VisitController {
    private VisitRepository visitRepository;

    public VisitController(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }
}
