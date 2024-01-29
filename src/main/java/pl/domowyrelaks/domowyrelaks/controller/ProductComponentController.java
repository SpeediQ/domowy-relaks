package pl.domowyrelaks.domowyrelaks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.domowyrelaks.domowyrelaks.model.Product;
import pl.domowyrelaks.domowyrelaks.model.ProductComponent;
import pl.domowyrelaks.domowyrelaks.model.Visit;
import pl.domowyrelaks.domowyrelaks.repository.ProductComponentRepository;
import pl.domowyrelaks.domowyrelaks.repository.ProductRepository;
import pl.domowyrelaks.domowyrelaks.repository.VisitRepository;

import java.util.Arrays;
import java.util.HashSet;

@Controller
public class ProductComponentController {
    private ProductComponentRepository productComponentRepository;
    private VisitRepository visitRepository;
    private ProductRepository productRepository;


    public ProductComponentController(ProductComponentRepository productComponentRepository, VisitRepository visitRepository, ProductRepository productRepository) {
        this.productComponentRepository = productComponentRepository;
        this.visitRepository = visitRepository;
        this.productRepository = productRepository;
    }

    public ProductComponent save(ProductComponent productComponent) {
        return productComponentRepository.save(productComponent);
    }

    @PostMapping("/pc/save")
    public String saveClient(@ModelAttribute ProductComponent productComponent, Model model) {
        productComponentRepository.save(productComponent);
        Visit visit = new Visit();
        Product product = new Product(productComponent);
        product.setVisit(visit);
        visit.setProductSet(new HashSet<>(Arrays.asList(product)));

        visit.setSummary(productComponent.getTitle());
        visit.setDesc(productComponent.getDesc());


        if (visit.getPreparationTime() == 0) {
            visit.setPreparationTime(product.getPreparationTime());
        }
        visitRepository.save(visit);
        model.addAttribute("visit", visit);

        return "visitForm";
    }
}
