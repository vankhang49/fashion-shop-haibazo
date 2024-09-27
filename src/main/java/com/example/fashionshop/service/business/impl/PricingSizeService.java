package com.example.fashionshop.service.business.impl;

import com.example.fashionshop.entities.PricingSize;
import com.example.fashionshop.repository.PricingSizeRepository;
import com.example.fashionshop.service.business.IPricingSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingSizeService implements IPricingSizeService {

    @Autowired
    private PricingSizeRepository pricingSizeRepository;

    @Override
    public List<PricingSize> findAll() {
        return pricingSizeRepository.findAll();
    }

    @Override
    public void save(PricingSize pricingSize) {
        pricingSizeRepository.save(pricingSize);
    }

    @Override
    public PricingSize findById(Long id) {
        return pricingSizeRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Long id) {
        pricingSizeRepository.deleteById(id);
    }
}
