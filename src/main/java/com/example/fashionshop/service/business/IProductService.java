package com.example.fashionshop.service.business;

import com.example.fashionshop.entities.Product;
import com.example.fashionshop.service.IGenerateService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface IProductService extends IGenerateService<Product> {

    Slice<Product> findAllProductsByProductNameAndCategoryNameAndStyleNameAndColorAndSizeBetweenPriceBeforeAndPriceAfter(String productName,
                                                                                                                         String categoryName,
                                                                                                                         String styleName,
                                                                                                                         String color,
                                                                                                                         String size,
                                                                                                                         Double priceBefore,
                                                                                                                         Double priceAfter,
                                                                                                                         Pageable pageable);

    void updateProduct(Product product);

    void softDeleteProduct(Long productId);

    void restoreProduct(Long productId);
}