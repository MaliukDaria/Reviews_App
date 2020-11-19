package com.example.demo.controller;

import com.example.demo.service.WordService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private static final String DEFAULT_NUMBER_OF_WORDS = "1000";
    private final WordService wordService;

    public ReviewController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/common-words")
    List<String> getMostCommonWords(
            @RequestParam(defaultValue = DEFAULT_NUMBER_OF_WORDS) int numberOfWords) {
        return wordService.getMostUsedWords(numberOfWords);
    }
}
