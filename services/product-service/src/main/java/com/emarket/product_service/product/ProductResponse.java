package com.emarket.product_service.product;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String name,
        String description,

        int availableQuantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription
) {
}
