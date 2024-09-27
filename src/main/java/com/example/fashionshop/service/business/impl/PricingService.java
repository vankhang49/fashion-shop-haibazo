package com.example.fashionshop.service.business.impl;

import com.example.fashionshop.entities.Pricing;
import com.example.fashionshop.repository.PricingRepository;
import com.example.fashionshop.service.business.IPricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingService implements IPricingService {

    @Autowired
    private PricingRepository pricingRepository;

    @Override
    public List<Pricing> searchAllByProductId(Long id) {
        return List.of();
    }

    @Override
    public List<Pricing> findAll() {
        return pricingRepository.findAll();
    }

    @Override
    public Pricing findById(Long id) {
        return pricingRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Pricing pricing) {
        pricingRepository.save(pricing);
    }

    @Override
    public void remove(Long id) {
        pricingRepository.deleteById(id);
    }

    @Override
    public void softDeletePricing(Long pricingId) {
        pricingRepository.softDeletePricingByPricingId(pricingId);
    }

    @Override
    public void restorePricing(Long pricingId) {
        pricingRepository.restorePricing(pricingId);
    }
}
