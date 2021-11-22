package nl.enshore.dbperformance.benchmark;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.enshore.dbperformance.model.Customer;
import nl.enshore.dbperformance.model.Product;
import nl.enshore.dbperformance.model.Purchase;
import nl.enshore.dbperformance.repository.CustomerRepository;
import nl.enshore.dbperformance.repository.ProductRepository;
import nl.enshore.dbperformance.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class Initializer {
    private static final Faker faker = new Faker();

    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    private final PurchaseRepository purchaseRepository;

    public void runAfterStartup(boolean doStuff) {
        if (doStuff) {
            long startTick = System.nanoTime();

            IntStream.rangeClosed(1, 500).forEach(i -> {
                this.customerRepository.saveAndFlush(Customer.createFakeCustomer(faker));
                this.productRepository.saveAndFlush(Product.createFakeProduct(faker));
            });

            this.generatePurchases();

            log.info("Elapsed time: {} milliseconds", (System.nanoTime() - startTick) / 1000000);
        }

        log.info("Number of customers: {}", this.customerRepository.count());
        log.info("Number of products: {}", this.productRepository.count());
        log.info("Number of purchases: {}", this.purchaseRepository.count());
    }

    private void generatePurchases() {
        List<Customer> customers = this.customerRepository.findAll();
        List<Product> products = this.productRepository.findAll();
        IntStream.rangeClosed(1, 300).forEach(i -> {
            Purchase purchase1 = new Purchase();
            purchase1.setCustomer(customers.get(i));
            purchase1.setProduct(products.get(i));

            Purchase purchase2 = new Purchase();
            purchase2.setCustomer(customers.get(i));
            purchase2.setProduct(products.get(i+1));

            purchaseRepository.save(purchase1);
        });
    }
}
