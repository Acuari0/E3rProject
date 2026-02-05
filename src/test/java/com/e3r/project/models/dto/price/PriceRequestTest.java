package com.e3r.project.models.dto.price;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static com.e3r.project.constants.Patterns.DATE_TIME_PATTERN;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@JsonTest
class PriceRequestTest {
    @Autowired
    private JacksonTester<PriceRequest> json;

    private Validator validator;

    @Test
    void shouldFailWhenFieldsAreNull() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        
        PriceRequest request = new PriceRequest(null, null, null);
        Set<ConstraintViolation<PriceRequest>> violations = validator.validate(request);
        
        assertThat(violations).hasSize(3);
    }

    @Test
    void shouldDeserializeJsonCorrectly() throws IOException {
        String dateStr = "2020-06-14-10.00.00";
        String content = "{\"applicationDate\":\"" + dateStr + "\", \"productId\":35455, \"brandId\":1}";

        PriceRequest result = json.parseObject(content);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        assertThat(result.getApplicationDate()).isEqualTo(LocalDateTime.parse(dateStr, formatter));
        assertThat(result.getProductId()).isEqualTo(35455L);
        assertThat(result.getBrandId()).isEqualTo(1L);
    }
}
