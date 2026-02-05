package com.e3r.project.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.e3r.project.constants.URLConstants.API_PRICES;
import static com.e3r.project.constants.URLConstants.FIND;
import com.e3r.project.models.dto.price.PriceRequest;
import com.e3r.project.models.dto.price.PriceResponse;
import com.e3r.project.services.PriceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_PRICES)
public class PriceController {
    
    private final PriceService priceService;
    
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }
    
    @GetMapping(FIND)
    public ResponseEntity<PriceResponse> findApplicablePrice(
            @Valid @ModelAttribute PriceRequest request) { 
            
        return priceService.findApplicablePrice(request)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
