package com.e3r.project.services;


import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e3r.project.models.Price;
import com.e3r.project.models.dto.price.PriceRequest;
import com.e3r.project.models.dto.price.PriceResponse;
import com.e3r.project.repositories.PriceRepository;

@Service
@Transactional(readOnly = true)
public class PriceServiceImpl implements PriceService {
    
    private final PriceRepository priceRepository;
    
    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }
    
    @Override
    public Optional<PriceResponse> findApplicablePrice(PriceRequest request) {
        Optional<Price> priceOpt = priceRepository.findApplicablePrice(
                request.getBrandId(),
                request.getProductId(),
                request.getApplicationDate()
        );
        
        return priceOpt.map(price -> new PriceResponse(
                price.getProductId(),
                price.getBrandId(),
                price.getPriceList(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPriceAmount(),
                price.getCurrency()
        ));
    }
}