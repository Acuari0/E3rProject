package com.e3r.project.services;

import java.util.Optional;

import com.e3r.project.models.dto.price.PriceRequest;
import com.e3r.project.models.dto.price.PriceResponse;

public interface PriceService {
    Optional<PriceResponse> findApplicablePrice(PriceRequest request);
}