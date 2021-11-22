package nl.enshore.dbperformance.benchmark;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.enshore.dbperformance.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlpgsqlBenchmarkService {

    private final CustomerRepository customerRepository;

    // Benchmark is als volgt;
    // Op basis van een lijst specifieke producten willen we klanten een bepaald kortingspercentage geven
    // Het idee is dat we bij elke customer kijken of er een aankoop is waarbij één van de producten gekocht is
    // Wanneer dat het geval is; krijgt de klant de korting
    @Transactional
    public void benchmark(String ids, Integer discount) {
        long startTick = System.nanoTime();

        customerRepository.calculateDiscounts(ids, discount);

        log.info("Processing duurde {} milliseconden", (System.nanoTime() - startTick) / 1000000);
    }
}
