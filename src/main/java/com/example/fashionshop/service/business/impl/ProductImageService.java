package com.example.fashionshop.service.business.impl;

import com.example.fashionshop.entities.ProductImage;
import com.example.fashionshop.repository.ProductImageRepository;
import com.example.fashionshop.service.business.IProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageService implements IProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;


    @Override
    public List<ProductImage> findAll() {
        return productImageRepository.findAll();
    }

    @Override
    public ProductImage findById(Long id) {
        return productImageRepository.findById(id).orElse(null);
    }

    @Override
    public void save(ProductImage productImage) {
        productImageRepository.save(productImage);
    }

    @Override
    public void remove(Long id) {
        productImageRepository.deleteById(id);
    }

    @Override
    public List<ProductImage> searchAllByProductId(Long productId) {
        return List.of();
    }
}
