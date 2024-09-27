package com.example.fashionshop.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "promotions")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_id")
    private Long promotionId;

    @Column(name = "promotion_name")
    @NotBlank(message = "Promotion name cannot be empty!")
    private String promotionName;

    @Column(name = "discount")
    @Min(value = 0, message = "The ratio cannot be less than 0 and must be between 0 and 1!")
    @Max(value = 1, message = "The ratio cannot be less than 0 and must be between 0 and 1!")
    private Double discount;
}
