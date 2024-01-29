package pl.domowyrelaks.domowyrelaks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.domowyrelaks.domowyrelaks.controller.ClientController;
import pl.domowyrelaks.domowyrelaks.controller.ProductComponentController;
import pl.domowyrelaks.domowyrelaks.controller.ProductController;
import pl.domowyrelaks.domowyrelaks.controller.VisitController;
import pl.domowyrelaks.domowyrelaks.model.Client;
import pl.domowyrelaks.domowyrelaks.model.Product;
import pl.domowyrelaks.domowyrelaks.model.ProductComponent;
import pl.domowyrelaks.domowyrelaks.model.Visit;
import pl.domowyrelaks.domowyrelaks.repository.VisitRepository;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashSet;
import java.util.Set;

@Service
public class VisitService {
    private VisitController visitController;
    private VisitRepository visitRepository;
    private ClientController clientController;
    private ProductComponentController productComponentController;
    private ProductController productController;

    @Autowired
    public VisitService(VisitController visitController, VisitRepository visitRepository, ClientController clientController, ProductComponentController productComponentController, ProductController productController) {
        this.visitController = visitController;
        this.visitRepository = visitRepository;
        this.clientController = clientController;
        this.productComponentController = productComponentController;
        this.productController = productController;
    }

    public void syncWithCloud() throws GeneralSecurityException, IOException {
        ProductComponent productComponent = new ProductComponent();
        productComponent.setTitle("ProductComponent");
        productComponentController.save(productComponent);
        Product product = new Product();
        product.setComponent(productComponent);
        product.setTitle("Component");

        Visit visit = new Visit();
        product.setVisit(visit);
        visit.setSummary("Test - MÓJ WIELKI TEST");
        visit.setDesc("Test Desc - WIELKI TEST");
        visit.setStart("2025-02-01T15:00:00+01:00");
        visit.setEnd("2025-02-01T18:30:00+01:00");
        visit.setPreparationTime(30);
        Client client = new Client();
        visit.setClient(client);
        Visit savedVisit = visitController.save(visit);
        productController.save(product);
        client.setName("Marcin");
        Set<Visit> visitSet = new HashSet<>();
        visitSet.add(savedVisit);
        client.setVisitSet(visitSet);
        clientController.save(client);



        Set<Product> productSet = new HashSet<>();
        productSet.add(product);

        visit.setProductSet(productSet);

        System.out.println("Dsdsa");
//        insertCalendarEventsByVisit(savedVisit);
    }

}
