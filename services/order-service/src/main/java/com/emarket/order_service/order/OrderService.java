package com.emarket.order_service.order;

import com.emarket.order_service.customer.CustomerClient;
import com.emarket.order_service.exception.BusinessException;
import com.emarket.order_service.product.ProductClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;

    public Integer createOrder(@Valid OrderRequest request) {

        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(
                        () -> new BusinessException("CANNOT CREATE ORDER - NO CUSTOMER EXISTS WITH ID: "+ request.customerId())
                );

        this.productClient.purchaseProduct(request.products());

        return null;
    }
}
