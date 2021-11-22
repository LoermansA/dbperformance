package nl.enshore.dbperformance.repository;

import nl.enshore.dbperformance.model.Customer;
import nl.enshore.dbperformance.model.Product;
import nl.enshore.dbperformance.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

//    List<Purchase> findAllByCustomer(Customer customer);

    List<Purchase> findAllByCustomerAndProductIn(Customer customer, List<Product> products);
}