package com.valle.payment.config;

import com.valle.payment.streaming.CheckoutProcessor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;
import service.PaymentService;

@Configuration
@EnableBinding(value = {CheckoutProcessor.class, PaymentService.class})
public class StreamingConfig {
}
