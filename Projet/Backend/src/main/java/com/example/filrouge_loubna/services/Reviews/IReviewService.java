package com.example.filrouge_loubna.services.Reviews;

import com.example.filrouge_loubna.dto.model.ReviewDto;

import java.util.List;

public interface IReviewService {
    ReviewDto AddReview(ReviewDto review);
    List<ReviewDto> getAllReviews(int page , int limit);
    boolean deleteReview(long id);
    ReviewDto updateReview(ReviewDto reviewsDto);
    ReviewDto getReviewById(long id);

    }
