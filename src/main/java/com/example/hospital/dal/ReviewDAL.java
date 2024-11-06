package com.example.hospital.dal;

import com.example.hospital.models.Review;
import org.springframework.stereotype.Component;
import com.example.hospital.repositories.ReviewRepository;

@Component
public class ReviewDAL {

    private final ReviewRepository reviewRepository;

    public ReviewDAL(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public void delete(Review review) {
        reviewRepository.delete(review);
    }
}
