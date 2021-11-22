package nl.enshore.dbperformance.benchmark;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SqlBenchmarkService {

    private final EntityManager entityManager;

    // Benchmark is als volgt;
    // Op basis van een lijst specifieke producten willen we klanten een bepaald kortingspercentage geven
    // Dit is de native query variant
    @Transactional
    public void benchmark(List<Long> ids, Integer discount) {
        long startTick = System.nanoTime();

        Query discounts = entityManager.createNativeQuery(
                "update customer set discount = :new_discount\n" +
                "where id in (select customer_id from purchase " +
                "where product_id in (:ids))");
        discounts.setParameter("ids", ids);
        discounts.setParameter("new_discount", discount);
        discounts.executeUpdate();

        log.info("Processing duurde {} milliseconden", (System.nanoTime() - startTick) / 1000000);
    }
}