package com.emarket.order_service.order;

import com.emarket.order_service.customer.CustomerClient;
import com.emarket.order_service.exception.BusinessException;
import com.emarket.order_service.kafka.OrderConfirmation;
import com.emarket.order_service.kafka.OrderProducer;
import com.emarket.order_service.orderline.OrderLineRequest;
import com.emarket.order_service.orderline.OrderLineService;
import com.emarket.order_service.product.ProductClient;
import com.emarket.order_service.purchase.PurchaseRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public Integer createOrder(@Valid OrderRequest request) {

        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(
                        () -> new BusinessException("CANNOT CREATE ORDER - NO CUSTOMER EXISTS WITH ID: "+ request.customerId())
                );

        var purchasedProducts = this.productClient.purchaseProducts(request.products());

        var order = this.repository.save(this.orderMapper.toOrder(request));

        for(PurchaseRequest purchaseRequest: request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                    null,
                        order.getId(),
                        purchaseRequest.productId(),
                        purchaseRequest.quantity()
                    )
            );
        }

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.totalAmount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );


        return order.getId();
    }
}
