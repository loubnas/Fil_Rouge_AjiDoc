package com.example.filrouge_loubna.repositories;

import com.example.filrouge_loubna.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends PagingAndSortingRepository<Review,Long> {
    Review findById(long id);
    Void deleteById(long id);
}
