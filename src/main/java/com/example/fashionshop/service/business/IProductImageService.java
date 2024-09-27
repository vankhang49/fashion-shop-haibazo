package com.example.fashionshop.service.business;

import com.example.fashionshop.entities.ProductImage;
import com.example.fashionshop.service.IGenerateService;

import java.util.List;

public interface IProductImageService extends IGenerateService<ProductImage> {
    List<ProductImage> searchAllByProductId(Long productId);
}
