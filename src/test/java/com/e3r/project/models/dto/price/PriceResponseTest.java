package com.e3r.project.models.dto.price;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import static com.e3r.project.constants.Patterns.DATE_TIME_PATTERN;

@JsonTest
class PriceResponseTest {

    @Autowired
    private JacksonTester<PriceResponse> json;

    @Test
    void shouldSerializeToJsonCorrectly() throws Exception {
      
        LocalDateTime start = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(2020, 12, 31, 23, 59, 59);
        
        PriceResponse response = new PriceResponse(
                35455L, 1L, 1L, start, end, 35.50, "EUR"
        );

       
        JsonContent<PriceResponse> result = json.write(response);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        
        assertThat(result).extractingJsonPathNumberValue("$.productId").isEqualTo(35455);
        assertThat(result).extractingJsonPathNumberValue("$.brandId").isEqualTo(1);
        assertThat(result).extractingJsonPathNumberValue("$.priceList").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.startDate").isEqualTo(start.format(formatter));
        assertThat(result).extractingJsonPathStringValue("$.endDate").isEqualTo(end.format(formatter));
        assertThat(result).extractingJsonPathNumberValue("$.priceAmount").isEqualTo(35.50);
        assertThat(result).extractingJsonPathStringValue("$.currency").isEqualTo("EUR");
    }

    @Test
    void shouldExcludeEmptyFields() throws Exception {
        PriceResponse response = new PriceResponse();
        response.setProductId(35455L);

        JsonContent<PriceResponse> result = json.write(response);

       assertThat(result)
        .hasJsonPathNumberValue("$.productId")
        .doesNotHaveJsonPath("$.brandId")
        .doesNotHaveJsonPath("$.startDate")
        .doesNotHaveJsonPath("$.priceAmount");
    }
}