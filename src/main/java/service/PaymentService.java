package service;

import com.valle.ecommerce.CheckoutCreatedEvent;
import com.valle.payment.entity.PaymentEntity;

import java.util.Optional;

public interface PaymentService {
    Optional<PaymentEntity> create(CheckoutCreatedEvent checkoutCreatedEvent);
}
