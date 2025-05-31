package com.emarket.notification_service.kafka;

import com.emarket.notification_service.kafka.payment.PaymentConfirmation;
import com.emarket.notification_service.notification.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository repository;
    //private final EmailService emailService;

    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) {

    }

}
