package pl.domowyrelaks.domowyrelaks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.domowyrelaks.domowyrelaks.model.Visit;

public interface VisitRepository extends JpaRepository<Visit, Integer> {
}
