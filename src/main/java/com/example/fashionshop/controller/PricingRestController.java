package com.example.fashionshop.controller;

import com.example.fashionshop.entities.Pricing;
import com.example.fashionshop.service.business.IPricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/pricing")
public class PricingRestController {

    @Autowired
    private IPricingService pricingService;

    @PatchMapping("/{pricingId}/soft-delete")
    public ResponseEntity<?> softDeletePricing(@PathVariable Long pricingId) {
        Pricing pricing = pricingService.findById(pricingId);
        if(pricing == null) {
            return ResponseEntity.notFound().build();
        }
        pricingService.softDeletePricing(pricingId);
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @PatchMapping("/{pricingId}/restore")
    public ResponseEntity<?> restorePricing(@PathVariable Long pricingId) {
        Pricing pricing = pricingService.findById(pricingId);
        if(pricing == null) {
            return ResponseEntity.notFound().build();
        }
        pricingService.restorePricing(pricingId);
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @DeleteMapping("/{pricingId}")
    public ResponseEntity<?> deletePricingId(@PathVariable Long pricingId) {
        Pricing pricing = pricingService.findById(pricingId);
        if(pricing == null) {
            return ResponseEntity.notFound().build();
        }
        pricingService.remove(pricingId);
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

}
