package nl.enshore.dbperformance.model;

import com.github.javafaker.Faker;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "discount", nullable = false)
    private Integer discount;

    public static Customer createFakeCustomer(Faker faker) {
        Customer cust = new Customer();
        cust.firstName = faker.name().firstName();
        cust.lastName = faker.name().lastName();
        // Iedereen default 0 korting
        cust.discount = 0;
        return cust;
    }
}