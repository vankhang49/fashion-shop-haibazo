package com.example.fashionshop.repository;

import com.example.fashionshop.entities.Pricing;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PricingRepository extends JpaRepository<Pricing, Long> {

    @Query(value = "SELECT pricing_code FROM pricings ORDER BY pricing_id DESC LIMIT 1", nativeQuery = true)
    String findLastPricingCode();

    @Transactional
    @Modifying
    @Query(value = "UPDATE pricings SET enabled = 0 WHERE pricing_id = :pricingId", nativeQuery = true)
    void softDeletePricingByPricingId(@Param("pricingId") Long pricingId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE pricings SET enabled = 1 WHERE pricing_id = :pricingId", nativeQuery = true)
    void restorePricing(@Param("pricingId") Long pricingId);
}
