package pl.domowyrelaks.domowyrelaks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.domowyrelaks.domowyrelaks.model.Client;
import pl.domowyrelaks.domowyrelaks.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
