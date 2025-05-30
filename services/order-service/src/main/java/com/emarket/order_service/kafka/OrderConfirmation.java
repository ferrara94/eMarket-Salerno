package com.emarket.order_service.kafka;

import com.emarket.order_service.customer.CustomerResponse;
import com.emarket.order_service.order.PaymentMethod;
import com.emarket.order_service.purchase.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
