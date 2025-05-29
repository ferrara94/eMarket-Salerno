package com.emarket.product_service.product;

import com.emarket.product_service.category.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
         Integer id,
         @NotNull(message = "PRODUCT NAME IS REQUIRED")
         String name,
         @NotNull(message = "PRODUCT DESCRIPTION IS REQUIRED")
         String description,
         @Positive(message = "AVAILABLE QUANTITY SHOULD BE POSITIVE")
         int availableQuantity,
         @Positive(message = "PRICE SHOULD BE POSITIVE")
         BigDecimal price,
         @NotNull(message = "PRODUCT CATEGORY IS REQUIRED")
         Integer categoryId
         ) {
}
