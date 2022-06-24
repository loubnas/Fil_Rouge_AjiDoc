package com.example.filrouge_loubna.repositories;

import com.example.filrouge_loubna.entities.Appointement;
import com.example.filrouge_loubna.entities.Message;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MessageRepository extends PagingAndSortingRepository<Message,Long> {
    List<Message> getAllBy();
}
