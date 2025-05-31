package com.emarket.notification_service.kafka.order;

import org.springframework.data.annotation.Id;

public record Customer(
         String id,
         String firstName,
         String lastName,
         String email
) {
}
