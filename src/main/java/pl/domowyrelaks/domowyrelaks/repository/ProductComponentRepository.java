package pl.domowyrelaks.domowyrelaks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.domowyrelaks.domowyrelaks.model.Client;
import pl.domowyrelaks.domowyrelaks.model.ProductComponent;

public interface ProductComponentRepository extends JpaRepository<ProductComponent, Integer> {
}
