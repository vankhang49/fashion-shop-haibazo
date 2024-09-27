package com.example.fashionshop.service.business;

import com.example.fashionshop.entities.Pricing;
import com.example.fashionshop.service.IGenerateService;

import java.util.List;

public interface IPricingService extends IGenerateService<Pricing> {
    List<Pricing> searchAllByProductId(Long id);

    void softDeletePricing(Long pricingId);

    void restorePricing(Long pricingId);
}
