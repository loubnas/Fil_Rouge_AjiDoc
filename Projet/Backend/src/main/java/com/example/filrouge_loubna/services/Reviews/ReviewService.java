package com.example.filrouge_loubna.services.Reviews;

import com.example.filrouge_loubna.dto.model.ReviewDto;
import com.example.filrouge_loubna.dto.model.UserDocumentDto;
import com.example.filrouge_loubna.dto.model.UserDto;
import com.example.filrouge_loubna.dto.services.IMapClassWithDto;
import com.example.filrouge_loubna.entities.MedicalOffice;
import com.example.filrouge_loubna.entities.Review;
import com.example.filrouge_loubna.entities.User;
import com.example.filrouge_loubna.entities.UserDocument;
import com.example.filrouge_loubna.repositories.MedicalOfficeRepository;
import com.example.filrouge_loubna.repositories.ReviewRepository;
import com.example.filrouge_loubna.repositories.UserDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService implements  IReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    MedicalOfficeRepository medicalOfficeRepository;

    @Autowired
    IMapClassWithDto<Review, ReviewDto> reviewsMapping;


    // Add review : -----------------------------------------------------------------------------

    @Override
    public ReviewDto AddReview(ReviewDto reviewDto) {
        Review review = reviewsMapping.convertToEntity(reviewDto, Review.class);
        review = reviewRepository.save(review);
      

        return reviewsMapping.convertToDto(review, ReviewDto.class);
    }


    // get all  reviews  :------------------------------------------------------------

    @Override
    public List<ReviewDto> getAllReviews(int page , int limit) {
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Review> reviews = reviewRepository.findAll(pageableRequest);
        return reviewsMapping.convertPageToListDto(reviews, ReviewDto.class);
    }

    // Delete Review  : ____________________________________________________________________________________

    @Override
    public boolean deleteReview(long id) {
        try {
           reviewRepository.deleteById(id);
        }catch (Exception ex){
            return false;
        }
        return true;
    }


    // Get review By Id  : ____________________________________________________________________________________

    @Override
    public ReviewDto getReviewById(long id) {
     Review review = reviewRepository.findById(id);
     return reviewsMapping.convertToDto(review, ReviewDto.class);

    }

    // Update Review : ____________________________________________________________________________________

    @Override
    public ReviewDto updateReview(ReviewDto reviewsDto) {
     Review review = reviewsMapping.convertToEntity(reviewsDto, Review.class);
     review= reviewRepository.save(review);
     return reviewsMapping.convertToDto(review, ReviewDto.class);


    }


}
