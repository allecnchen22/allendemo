package com.example.allendemo;
	
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.metrics.export.prometheus.EnablePrometheusMetrics;

import io.prometheus.client.hotspot.DefaultExports;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;

@SpringBootApplication
@EnableSpringBootMetricsCollector
@EnablePrometheusMetrics
public class AllendemoApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(AllendemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		DefaultExports.initialize();
	}
	
	

}
