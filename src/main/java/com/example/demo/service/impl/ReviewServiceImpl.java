package com.example.demo.service.impl;

import com.example.demo.model.Review;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.service.ReviewService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review add(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void addAll(List<Review> reviews) {
        reviewRepository.saveAll(reviews);
    }

    @Override
    public List<String> getMostUsedWords(int numberOfWords) {
        List<String> allText = reviewRepository.getAllText();
        Map<String, Long> words = new HashMap<>();
        allText.stream()
                .flatMap(str -> Arrays.stream(str.toLowerCase().split(" ")))
                .forEach(e -> words.merge(e, 1L, (key, value) -> words.get(e) + 1L));
        return words.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .limit(numberOfWords)
                .collect(Collectors.toList());
    }
}
