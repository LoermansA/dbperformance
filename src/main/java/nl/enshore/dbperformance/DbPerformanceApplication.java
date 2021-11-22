package nl.enshore.dbperformance;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.enshore.dbperformance.benchmark.Initializer;
import nl.enshore.dbperformance.benchmark.JpaBenchmarkService;
import nl.enshore.dbperformance.benchmark.PlpgsqlBenchmarkService;
import nl.enshore.dbperformance.benchmark.SqlBenchmarkService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class DbPerformanceApplication {

	private final Initializer initializr;

	private final JpaBenchmarkService jpaBenchmarkService;

	private final PlpgsqlBenchmarkService plpgsqlBenchmarkService;

	private final SqlBenchmarkService sqlBenchmarkService;

	public static void main(String[] args) {
		SpringApplication.run(DbPerformanceApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
		initializr.runAfterStartup(false);

		sqlBenchmarkService.benchmark(Arrays.asList(2l, 4l, 6l, 8l, 10l, 12l, 14l, 16l), 15);
		plpgsqlBenchmarkService.benchmark("2,4,6,8,10,12,14,16", 35);
		jpaBenchmarkService.benchmark(Arrays.asList(2l, 4l, 6l, 8l, 10l, 12l, 14l, 16l), 25);

		sqlBenchmarkService.benchmark(Arrays.asList(2l, 4l, 6l, 8l, 10l, 12l, 14l, 16l), 15);
		plpgsqlBenchmarkService.benchmark("2,4,6,8,10,12,14,16", 35);
		jpaBenchmarkService.benchmark(Arrays.asList(2l, 4l, 6l, 8l, 10l, 12l, 14l, 16l), 25);
	}
}
