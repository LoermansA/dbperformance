package nl.enshore.dbperformance.repository;

import nl.enshore.dbperformance.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
