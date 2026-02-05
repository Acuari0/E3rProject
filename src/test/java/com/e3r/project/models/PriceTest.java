package com.e3r.project.models;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class PriceTest {
    @Test
    void buildConstructor() {
        
        Price price = Price.builder()
                .brandId(1L)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .priceList(1L)
                .productId(35455L)
                .priority(0)
                .priceAmount(35.50)
                .currency("EUR")
                .build();

        assertThat(price.getBrandId()).isEqualTo(1L);
        assertThat(price.getCurrency()).isEqualTo("EUR");
    }

    @Test
    void noArgsConstructorTest() {
        Price price = new Price();
        assertThat(price).isNotNull();
    }
}