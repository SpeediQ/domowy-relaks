package pl.domowyrelaks.domowyrelaks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.domowyrelaks.domowyrelaks.model.Product;
import pl.domowyrelaks.domowyrelaks.model.ProductComponent;
import pl.domowyrelaks.domowyrelaks.model.Visit;
import pl.domowyrelaks.domowyrelaks.repository.VisitRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Controller
public class VisitController {
    private VisitRepository visitRepository;

    public VisitController(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }
    @PostMapping("/v/save")
    public String saveClient(@ModelAttribute Visit visit) {
        Set<Product> productSet = visit.getProductSet();

        return "visitForm";
    }
}
