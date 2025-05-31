package com.emarket.order_service.payment;

import com.emarket.order_service.customer.CustomerResponse;
import com.emarket.order_service.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
