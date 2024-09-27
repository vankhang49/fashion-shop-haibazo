package com.example.fashionshop.service.business.impl;

import com.example.fashionshop.entities.Pricing;
import com.example.fashionshop.entities.Product;
import com.example.fashionshop.entities.ProductImage;
import com.example.fashionshop.repository.PricingRepository;
import com.example.fashionshop.repository.ProductRepository;
import com.example.fashionshop.service.business.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PricingRepository pricingRepository;

    @Override
    public Slice<Product> findAllProductsByProductNameAndCategoryNameAndStyleNameAndColorAndSizeBetweenPriceBeforeAndPriceAfter(String productName,
                                                                                                                                String categoryName,
                                                                                                                                String styleName,
                                                                                                                                String color,
                                                                                                                                String size,
                                                                                                                                Double priceBefore,
                                                                                                                                Double priceAfter,
                                                                                                                                Pageable pageable) {
        return productRepository.findAllProductsByProductNameAndCategoryNameAndStyleNameAndColorAndSizeBetweenPriceBeforeAndPriceAfter(productName,
                categoryName, styleName, color, size, priceBefore, priceAfter, pageable);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void save(@Valid Product product) {
        int productCodeNumber = findLastProductCode() + 1;
        product.setProductCode(generateCode(productCodeNumber, "P-"));
        product.setDateCreate(LocalDate.now());

        int lastPricingCodeNumber = findLastPricingCode();
        for (Pricing pricing : product.getPricingList()) {
            lastPricingCodeNumber++;
            pricing.setPricingCode(generateCode(lastPricingCodeNumber, "PC-"));
            pricing.setProduct(product);
        }
        for (ProductImage productImage : product.getProductImages()) {
            productImage.setProduct(product);
        }

        productRepository.save(product);
    }

    @Override
    public void updateProduct(@Valid Product product) {

        int lastPricingCodeNumber = findLastPricingCode();
        for (Pricing pricing : product.getPricingList()) {
            if (pricing.getProduct() == null) {
                lastPricingCodeNumber++;
                pricing.setPricingCode(generateCode(lastPricingCodeNumber, "PC-"));
                System.out.println(pricing);
                pricing.setProduct(product);
            }
        }

        for (ProductImage productImage : product.getProductImages()) {
            if (productImage.getProduct() == null) {
                System.out.println(productImage);
                productImage.setProduct(product);
            }
        }
        productRepository.save(product);
    }

    @Override
    public void softDeleteProduct(Long productId) {
        productRepository.softDeleteProductByProductId(productId);
    }

    @Override
    public void restoreProduct(Long productId) {
        productRepository.restoreProduct(productId);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    private int findLastProductCode() {
        String productCode = productRepository.findLastProductCode();
        int codeNumber = 0;
        if (productCode != null && !productCode.isEmpty()) {
            codeNumber = Integer.parseInt(productCode.substring(2));
        }
        return codeNumber;
    }

    private int findLastPricingCode() {
        String lastCode = pricingRepository.findLastPricingCode();
        int codeNumber = 0;
        if (lastCode != null && !lastCode.isEmpty()) {
            codeNumber = Integer.parseInt(lastCode.substring(3));
        }
        return codeNumber;
    }

    private String generateCode(int numberCode, String type) {
        if (numberCode < 10) {
            type += "0000" + numberCode;
        } else if (numberCode < 100) {
            type += "000" + numberCode;
        } else if (numberCode < 1000) {
            type += "00" + numberCode;
        } else if (numberCode < 10000) {
            type += "0" + numberCode;
        } else {
            type += numberCode;
        }
        return type;
    }


}
