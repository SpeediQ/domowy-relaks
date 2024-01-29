package pl.domowyrelaks.domowyrelaks.controller;

import org.springframework.stereotype.Controller;
import pl.domowyrelaks.domowyrelaks.model.Product;
import pl.domowyrelaks.domowyrelaks.model.Visit;
import pl.domowyrelaks.domowyrelaks.repository.ProductRepository;
import pl.domowyrelaks.domowyrelaks.repository.VisitRepository;

@Controller
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
