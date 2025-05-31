package com.emarket.notification_service.notification;

import com.emarket.notification_service.kafka.payment.PaymentConfirmation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<PaymentConfirmation, String> {

}
