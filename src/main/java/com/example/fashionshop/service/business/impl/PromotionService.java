package com.example.fashionshop.service.business.impl;

import com.example.fashionshop.entities.Promotion;
import com.example.fashionshop.repository.IPromotionRepository;
import com.example.fashionshop.service.business.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService implements IPromotionService {
    @Autowired
    private IPromotionRepository promotionRepository;

    @Override
    public List<Promotion> findAll() {
        return promotionRepository.findAll();
    }

    @Override
    public Promotion findById(Long id) {
        return promotionRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Promotion promotion) {
        promotionRepository.save(promotion);
    }

    @Override
    public void remove(Long id) {
        promotionRepository.deleteById(id);
    }
}
