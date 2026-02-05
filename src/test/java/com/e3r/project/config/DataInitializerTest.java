package com.e3r.project.config;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.e3r.project.models.Price;
import com.e3r.project.repositories.PriceRepository;

@SpringBootTest
class DataInitializerTest {

    @Autowired
    private PriceRepository priceRepository;

    @Test
    void shouldLoadInitialPrices() {
        List<Price> prices = priceRepository.findAll();

        assertThat(prices).hasSize(4);

        boolean existsFirstPrice = prices.stream()
                .anyMatch(p -> p.getPriceAmount().equals(35.50) && p.getPriceList().equals(1L));
        
        assertThat(existsFirstPrice).isTrue();
    }
}