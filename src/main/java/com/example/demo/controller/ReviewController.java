package com.example.demo.controller;

import com.example.demo.service.ReviewService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private static final String NUMBER_OF_WORDS = "1000";
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/common-words")
    List<String> getMostCommonWords(
            @RequestParam(defaultValue = NUMBER_OF_WORDS) int numberOfWords) {
        return reviewService.getMostUsedWords(numberOfWords);
    }
}
