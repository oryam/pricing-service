package com.abb.demo.pricing.application.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.abb.demo.pricing.application.config.AppConfig;
import com.abb.demo.pricing.repository.stock.StockRepository;

@SpringBootApplication
// @EnableScheduling
@Import(AppConfig.class)
public class PricingApplication {

    @Autowired
    private StockRepository stockRepository;

    public static void main(String[] args) {
        SpringApplication.run(PricingApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            stockRepository.createStocks();
        };
    }

}
