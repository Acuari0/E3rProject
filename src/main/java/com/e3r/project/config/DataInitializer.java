package com.e3r.project.config;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.e3r.project.models.Price;
import com.e3r.project.repositories.PriceRepository;

@Configuration
public class DataInitializer {
    
    @Bean
    public CommandLineRunner initDatabase(PriceRepository priceRepository) {

        return args -> {
            priceRepository.save(Price
                .builder()
                .brandId(1L)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .productId(35455L)
                .priority(0)
                .priceAmount(35.50)
                .currency("EUR")
                .build());
            
            priceRepository.save(Price
                .builder()
                .brandId(1L)
                .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
                .endDate(LocalDateTime.of(2020, 6, 14, 18, 30, 0))
                .productId(35455L)
                .priority(1)
                .priceAmount(25.45)
                .currency("EUR")
                .build());


            priceRepository.save(Price
                .builder()
                .brandId(1L)
                .startDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0))
                .productId(35455L)
                .priority(1)
                .priceAmount(30.50)
                .currency("EUR")
                .build());


            priceRepository.save(Price
                .builder()
                .brandId(1L)
                .startDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .productId(35455L)
                .priority(1)
                .priceAmount(38.95)
                .currency("EUR")
                .build());
        };
    }
}