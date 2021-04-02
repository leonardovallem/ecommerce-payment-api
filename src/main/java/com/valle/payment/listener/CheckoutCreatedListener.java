package com.valle.payment.listener;

import com.valle.ecommerce.CheckoutCreatedEvent;
import com.valle.ecommerce.payment.event.PaymentDoneEvent;
import com.valle.payment.streaming.CheckoutProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import service.PaymentService;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckoutCreatedListener {
    private final CheckoutProcessor checkoutProcessor;
    private final PaymentService paymentService;

    @StreamListener(CheckoutProcessor.INPUT)
    public void handler(CheckoutCreatedEvent event) {
        final var paymentEntity = paymentService.create(event).orElseThrow();
        final PaymentDoneEvent paymentDoneEvent = PaymentDoneEvent.newBuilder()
                .setCheckoutCode(paymentEntity.getCheckoutCode())
                .setPaymentCode(paymentEntity.getCode())
                .build();
        checkoutProcessor.output().send(MessageBuilder.withPayload(paymentDoneEvent).build());
    }
}
