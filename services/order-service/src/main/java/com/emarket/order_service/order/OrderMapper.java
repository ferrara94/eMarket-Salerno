package com.emarket.order_service.order;

import org.springframework.stereotype.Service;

@Service

public class OrderMapper {

    public Order toOrder(OrderRequest request){
        return Order.builder()
                .id(request.id())
                .reference(request.reference())
                .totalAmount(request.totalAmount())
                .paymentMethod(request.paymentMethod())
                .customerId(request.customerId())
                .build();
    }

}
