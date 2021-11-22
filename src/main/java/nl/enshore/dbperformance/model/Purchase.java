package nl.enshore.dbperformance.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="purchase")
public class Purchase {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "purchase_datetime")
    private LocalDateTime purchaseDateTime = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
}
