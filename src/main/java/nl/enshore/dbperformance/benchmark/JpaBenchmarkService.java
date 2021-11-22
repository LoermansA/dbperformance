package nl.enshore.dbperformance.benchmark;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.enshore.dbperformance.model.Product;
import nl.enshore.dbperformance.repository.CustomerRepository;
import nl.enshore.dbperformance.repository.ProductRepository;
import nl.enshore.dbperformance.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JpaBenchmarkService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;

    // Benchmark is als volgt;
    // Op basis van een lijst specifieke producten willen we klanten een bepaald kortingspercentage geven
    // Het idee is dat we bij elke customer kijken of er een aankoop is waarbij één van de producten gekocht is
    // Wanneer dat het geval is; krijgt de klant de korting
    public void benchmark(List<Long> ids, Integer discount) {
        // Start your engines
        long startTick = System.nanoTime();

        List<Product> products = productRepository.findAllById(ids);
        // Itereer over alle Customers
        this.customerRepository.findAll().forEach(customer -> {
            // Vind alle purchases per Customer waarin één van de producten zit
            this.purchaseRepository.findAllByCustomerAndProductIn(customer, products).forEach(purchase -> {
                customer.setDiscount(discount);
                customerRepository.save(customer);
            });
        });

        log.info("Processing duurde {} milliseconden", (System.nanoTime() - startTick) / 1000000);
    }
}
