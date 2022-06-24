package com.example.filrouge_loubna.services.Payment;


import com.example.filrouge_loubna.dto.model.PaymentDto;
import com.example.filrouge_loubna.dto.services.IMapClassWithDto;
import com.example.filrouge_loubna.entities.Appointement;
import com.example.filrouge_loubna.entities.Payment;
import com.example.filrouge_loubna.entities.User;
import com.example.filrouge_loubna.repositories.AppointementRepository;
import com.example.filrouge_loubna.repositories.PaymentRepository;
import com.example.filrouge_loubna.services.Payment.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    AppointementRepository appointementRepository;

    @Autowired
    IMapClassWithDto<Payment, PaymentDto> paymentMapping;


    // Add payment : -----------------------------------------------------------------------------

    @Override
    public PaymentDto AddPayment(PaymentDto paymentDto) {
        Payment payment = paymentMapping.convertToEntity(paymentDto, Payment.class);
        System.out.println(paymentDto);
        System.out.println(payment);

        Optional<Appointement> appointement=appointementRepository.findById(paymentDto.getAppointement().getId());
        payment.setAppointement(appointement.get());
        payment = paymentRepository.save(payment);
        return paymentMapping.convertToDto(payment, PaymentDto.class);
    }


    // get all  payment  :------------------------------------------------------------

    @Override
    public List<PaymentDto> getAllPayments(int page , int limit) {
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Payment> payment = paymentRepository.findAll(pageableRequest);
        return paymentMapping.convertPageToListDto(payment, PaymentDto.class);
    }

    // Delete Review  : ____________________________________________________________________________________

    @Override
    public boolean deletePayment(long id) {
        try {
            paymentRepository.deleteById(id);
        }catch (Exception ex){
            return false;
        }
        return true;
    }


    // Get payment By Id  : ____________________________________________________________________________________

    @Override
    public PaymentDto getPaymentById(long id) {
        Payment payment = paymentRepository.findById(id);
        return paymentMapping.convertToDto(payment, PaymentDto.class);

    }

    // Update Review : ____________________________________________________________________________________

    @Override
    public PaymentDto updatePayment(PaymentDto paymentDto) {
        Payment payment = paymentMapping.convertToEntity(paymentDto, Payment.class);

        Appointement appointement=appointementRepository.findById(paymentDto.getId());
        payment.setAppointement(appointement);
        payment= paymentRepository.save(payment);
        return paymentMapping.convertToDto(payment, PaymentDto.class);


    }


}

