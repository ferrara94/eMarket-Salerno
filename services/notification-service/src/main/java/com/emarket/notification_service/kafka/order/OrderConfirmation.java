package com.emarket.notification_service.kafka.order;

import com.emarket.notification_service.kafka.payment.PaymentMethod;

import java.math.BigDecimal;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
