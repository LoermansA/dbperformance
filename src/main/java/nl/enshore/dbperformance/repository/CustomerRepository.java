package nl.enshore.dbperformance.repository;

import nl.enshore.dbperformance.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Modifying
    @Query(value = "CALL calc_discount(:ids, :new_discount);", nativeQuery = true)
    void calculateDiscounts(@Param("ids") String ids, @Param("new_discount") Integer newDiscount);
}