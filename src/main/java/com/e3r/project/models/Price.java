package com.e3r.project.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor 
@AllArgsConstructor
@Table(name = "PRICES")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Price implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Column(name = "BRAND_ID")
    private Long brandId;
    
    @Column(name = "START_DATE")
    private LocalDateTime startDate;
    
    @Column(name = "END_DATE")
    private LocalDateTime endDate;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRICE_LIST")
    private Long  priceList;
    
    @Column(name = "PRODUCT_ID")
    private Long productId;
    
    @Column(name = "PRIORITY")
    private Integer priority;
    
    @Column(name = "PRICE")
    private Double priceAmount;
    
    @Column(name = "CURR")
    private String currency;
}