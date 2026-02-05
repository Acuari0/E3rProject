package com.e3r.project.models.dto.price;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import static com.e3r.project.constants.Patterns.DATE_TIME_PATTERN;
import static com.e3r.project.constants.ResponseMessage.FIELD_REQUIRED;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceRequest {
    
    @NotNull(message = FIELD_REQUIRED)
    @JsonFormat(pattern = DATE_TIME_PATTERN)
    @DateTimeFormat(pattern =  DATE_TIME_PATTERN)
    private LocalDateTime applicationDate;
    
    @NotNull(message = FIELD_REQUIRED)
    private Long productId;
    
    @NotNull(message = FIELD_REQUIRED)
    private Long brandId;
}
