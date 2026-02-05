package com.e3r.project.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.e3r.project.models.Price;

@DataJpaTest
class PriceRepositoryTest {

    @Autowired
    private PriceRepository priceRepository;

    @Test
    void shouldFindPriceWithHighestPriority() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0);
        
        Price lowPriority = Price.builder()
                .brandId(1L).productId(35455L).priority(0).priceAmount(35.50)
                .startDate(date.minusDays(1)).endDate(date.plusDays(1)).build();
        
        Price highPriority = Price.builder()
                .brandId(1L).productId(35455L).priority(1).priceAmount(25.45)
                .startDate(date.minusHours(1)).endDate(date.plusHours(1)).build();

        priceRepository.save(lowPriority);
        priceRepository.save(highPriority);

        Optional<Price> result = priceRepository.findApplicablePrice(1L, 35455L, date);

        assertThat(result)
            .isPresent()
            .hasValueSatisfying(p -> {
                assertThat(p.getPriority()).isEqualTo(1);
                assertThat(p.getPriceAmount()).isEqualTo(25.45);
            });
    }

    @Test
    void shouldReturnEmptyWhenNoPriceExistsForDate() {
        Optional<Price> result = priceRepository.findApplicablePrice(
            1L, 35455L, LocalDateTime.of(1900, 1, 1, 0, 0));

        assertThat(result).isEmpty();
    }
}
