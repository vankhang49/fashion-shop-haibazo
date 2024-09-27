package com.example.fashionshop.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_code", unique = true)
    private String productCode;

    @Column(name = "product_name")
    @NotBlank(message = "Product name cannot be empty!")
    @Size(min = 3, max = 50, message = "Product names must be between 3 and 50 characters.")
    private String productName;

    @Column(name = "date_create")
    private LocalDate dateCreate;

    @Column(name = "product_desc", columnDefinition = "TEXT")
    @NotBlank(message = "Product description cannot be empty!")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "Category cannot be left blank!")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "style_id")
    @NotNull(message = "Product style cannot be empty!")
    private Style style;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Size(min = 1, message = "Product must have at least one pricing")
    private List<Pricing> pricingList;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ProductImage> productImages;

    @Column(name = "enabled" ,nullable = false)
    private Boolean enabled=true;
}
