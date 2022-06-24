package com.example.filrouge_loubna.services.Payment;

import com.example.filrouge_loubna.dto.model.PaymentDto;

import java.util.List;

public interface IPaymentService {

    PaymentDto AddPayment(PaymentDto payment);
    List<PaymentDto> getAllPayments(int page , int limit);
    boolean deletePayment(long id);
    PaymentDto updatePayment(PaymentDto payment);
    PaymentDto getPaymentById(long id);
}
