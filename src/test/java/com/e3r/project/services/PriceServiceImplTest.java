package com.e3r.project.services;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.e3r.project.models.Price;
import com.e3r.project.models.dto.price.PriceRequest;
import com.e3r.project.models.dto.price.PriceResponse;
import com.e3r.project.repositories.PriceRepository;

@ExtendWith(MockitoExtension.class) 
class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceService;

    @Test
    void shouldReturnMappedPriceResponse() {
        LocalDateTime now = LocalDateTime.now();
        PriceRequest request = new PriceRequest();

        request.setBrandId(1L);
        request.setProductId(35455L);
        request.setApplicationDate(now);
        
        Price mockPrice = Price.builder()
                .productId(35455L)
                .brandId(1L)
                .priceList(1L)
                .startDate(now.minusDays(1))
                .endDate(now.plusDays(1))
                .priceAmount(35.50)
                .currency("EUR")
                .build();

        when(priceRepository.findApplicablePrice(1L, 35455L, now))
            .thenReturn(Optional.of(mockPrice));

        Optional<PriceResponse> result = priceService.findApplicablePrice(request);

        assertThat(result).isPresent();
        assertThat(result.get())
            .extracting(PriceResponse::getProductId, PriceResponse::getPriceAmount, PriceResponse::getCurrency)
            .containsExactly(35455L, 35.50, "EUR");

        verify(priceRepository).findApplicablePrice(1L, 35455L, now);
    }

    @Test
    void shouldReturnEmptyWhenPriceNotFound() {
       
        LocalDateTime now = LocalDateTime.now();
        PriceRequest request = new PriceRequest();

        request.setBrandId(1L);
        request.setProductId(35455L);
        request.setApplicationDate(now);

        when(priceRepository.findApplicablePrice(any(), any(), any()))
            .thenReturn(Optional.empty());

        Optional<PriceResponse> result = priceService.findApplicablePrice(request);

        assertThat(result).isEmpty();
    }
}