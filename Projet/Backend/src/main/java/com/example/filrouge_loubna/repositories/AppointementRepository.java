package com.example.filrouge_loubna.repositories;

import com.example.filrouge_loubna.entities.Appointement;
import com.example.filrouge_loubna.entities.Review;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AppointementRepository  extends PagingAndSortingRepository<Appointement,Long> {
    Appointement findById(long id);
    Void deleteById(long id);
    List<Appointement> findAllByUserId(long id);
}
