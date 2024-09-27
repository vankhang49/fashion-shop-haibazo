package com.example.fashionshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pricings")
public class Pricing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pricing_id")
    private Long pricingId;

    @Column(name = "pricing_name", nullable = false)
    @NotBlank(message = "Detailed product names cannot be left blank!")
    @Size(min = 3, max = 50, message = "Detailed product names must be from 3 to 50 characters.")
    private String pricingName;

    @Column(name = "pricing_code", nullable = false, unique = true)
    private String pricingCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    @NotNull(message = "Products cannot be empty!")
    private Product product;

    @Column(name = "price", nullable = false)
    @NotNull(message = "Price cannot be left blank!")
    @Min(value = 0, message = "Price must be greater than or equal to 0!")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private PricingSize size;

    @Column(name = "quantity", columnDefinition = "integer default 0", nullable = false)
    @NotNull(message = "Inventory quantity cannot be left blank!")
    @Min(value = 0, message = "Inventory quantity must be greater than or equal to 0!")
    private Integer quantity = 0;

    @ManyToOne
    @JoinColumn(name = "color_id")
    @NotNull(message = "Colors cannot be left blank!")
    private Color color;

    @Column(name = "pricing_image", nullable = false)
    @NotBlank(message = "Detailed product images cannot be left blank!")
    private String pricingImgUrl;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @Column(name = "enabled" , nullable = false)
    private Boolean enabled=true;
}
