package com.emarket.order_service.purchase;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "PRODUCT IS MANDATORY")
        Integer productId,
        @Positive(message = "QUANTITY IS MANDATORY")
        Integer quantity
) {
}
