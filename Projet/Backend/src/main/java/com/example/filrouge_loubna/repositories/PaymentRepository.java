package com.example.filrouge_loubna.repositories;

import com.example.filrouge_loubna.entities.Payment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaymentRepository  extends PagingAndSortingRepository<Payment,Long> {
    Payment findById(long id);
    Void deleteById(long id);
}

