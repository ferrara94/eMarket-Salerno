package com.emarket.order_service.order;

import com.emarket.order_service.purchase.PurchaseRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,

        String reference,
        @Positive(message = "ORDER AMOUNT SHOULD BE POSITIVE")
        BigDecimal totalAmount,
        @NotNull(message = "PAYMENT METHOD SHOULD BE PRECISED")
        PaymentMethod paymentMethod,

        @NotNull(message = "CUSTOMER SHOULD BE PRESENT")
        String customerId,
        @NotEmpty(message = "YOU SHOULD AT LEAST PURCHASE ONE PRODUCT")
        List<PurchaseRequest> products
) {
}
