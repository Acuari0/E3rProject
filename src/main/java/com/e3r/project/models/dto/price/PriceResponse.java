package com.e3r.project.models.dto.price;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import static com.e3r.project.constants.Patterns.DATE_TIME_PATTERN;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PriceResponse {
    
    private Long productId;

    private Long brandId;

    private Long priceList;
    
    @JsonFormat(pattern = DATE_TIME_PATTERN)
    @DateTimeFormat(pattern =  DATE_TIME_PATTERN)
    private LocalDateTime startDate;
    
    @JsonFormat(pattern = DATE_TIME_PATTERN)
    @DateTimeFormat(pattern =  DATE_TIME_PATTERN)
    private LocalDateTime endDate;
    
    private Double priceAmount;

    private String currency;
    
}