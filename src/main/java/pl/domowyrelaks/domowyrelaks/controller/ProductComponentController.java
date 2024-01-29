package pl.domowyrelaks.domowyrelaks.controller;

import org.springframework.stereotype.Controller;
import pl.domowyrelaks.domowyrelaks.model.ProductComponent;
import pl.domowyrelaks.domowyrelaks.model.Visit;
import pl.domowyrelaks.domowyrelaks.repository.ProductComponentRepository;
import pl.domowyrelaks.domowyrelaks.repository.VisitRepository;

@Controller
public class ProductComponentController {
    private ProductComponentRepository productComponentRepository;

    public ProductComponentController(ProductComponentRepository productComponentRepository) {
        this.productComponentRepository = productComponentRepository;
    }

    public ProductComponent save(ProductComponent productComponent) {
        return productComponentRepository.save(productComponent);
    }
}
