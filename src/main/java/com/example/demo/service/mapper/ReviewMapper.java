package com.example.demo.service.mapper;

import com.example.demo.model.Review;
import com.example.demo.model.ReviewDto;
import org.springframework.stereotype.Service;

@Service
public class ReviewMapper {
    public Review mapToReview(ReviewDto reviewDto) {
        Review review = new Review();
        review.setExternalId(reviewDto.getId());
        review.setDateTime(reviewDto.getDateTime());
        review.setSummary(reviewDto.getSummary());
        review.setText(reviewDto.getText());
        review.setScore(reviewDto.getScore());
        return review;
    }
}
