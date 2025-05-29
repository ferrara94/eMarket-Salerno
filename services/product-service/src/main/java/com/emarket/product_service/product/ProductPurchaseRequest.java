package com.emarket.product_service.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "PRODUCT IS MANDATORY")
        Integer productId,
        @NotNull(message = "QUANTITY IS MANDATORY")
        int quantity
) {
}
