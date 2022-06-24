package com.example.filrouge_loubna.repositories;

import com.example.filrouge_loubna.entities.Appointement;
import com.example.filrouge_loubna.entities.OnlinePaymentInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OnlinePaymentInfoRepository extends PagingAndSortingRepository<OnlinePaymentInfo,Long> {
    OnlinePaymentInfo findById(long id);
    Void deleteById(long id);
}

