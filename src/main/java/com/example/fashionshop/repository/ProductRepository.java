package com.example.fashionshop.repository;

import com.example.fashionshop.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p.product_id, p.product_name, p.product_code, p.date_create, p.product_desc, p.enabled, " +
            "p.category_id, p.style_id " +
            "FROM products p " +
            "JOIN categories c ON p.category_id = c.category_id " +
            "JOIN styles s ON p.style_id = s.style_id " +
            "JOIN pricings pr ON p.product_id = pr.product_id " +
            "JOIN colors co ON pr.color_id = co.color_id " +
            "JOIN sizes si ON pr.size_id = si.size_id " +
            "WHERE p.product_name LIKE %:nameSearch% " +
            "AND c.category_name LIKE %:categoryName% " +
            "AND s.style_name LIKE %:styleName% " +
            "AND co.color_name LIKE %:colorName% " +
            "AND si.size_name LIKE %:size% " +
            "AND pr.price BETWEEN :priceBefore AND :priceAfter", nativeQuery = true)
    Slice<Product> findAllProductsByProductNameAndCategoryNameAndStyleNameAndColorAndSizeBetweenPriceBeforeAndPriceAfter(
            @Param("nameSearch") String nameSearch,
            @Param("categoryName") String categoryName,
            @Param("styleName") String styleName,
            @Param("colorName") String colorName,
            @Param("size") String size,
            @Param("priceBefore") Double priceBefore,
            @Param("priceAfter") Double priceAfter,
            Pageable pageable);


    @Transactional
    @Modifying
    @Query(value = "UPDATE products SET enabled = 0 WHERE product_id = :productId", nativeQuery = true)
    void softDeleteProductByProductId(@Param("productId") Long productId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE products SET enabled = 1 WHERE product_id = :productId", nativeQuery = true)
    void restoreProduct(@Param("productId") Long productId);

    @Query(value = "SELECT product_code FROM products ORDER BY product_id DESC LIMIT 1", nativeQuery = true)
    String findLastProductCode();
}
