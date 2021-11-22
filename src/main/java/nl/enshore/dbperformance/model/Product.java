package nl.enshore.dbperformance.model;

import com.github.javafaker.Faker;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name="price", nullable = false)
    private Double price;

    public static Product createFakeProduct(Faker faker) {
        Product product = new Product();

        product.name = faker.commerce().productName();
        product.price = Double.parseDouble(faker.commerce().price());

        return product;
    }
}
