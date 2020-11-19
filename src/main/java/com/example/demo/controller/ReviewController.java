package com.example.demo.controller;

import com.example.demo.service.WordService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private static final String NUMBER_OF_WORDS = "1000";
    private final WordService wordService;

    public ReviewController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/common-words")
    @ApiOperation(value = "Get most common words in reviews")
    List<String> getMostCommonWords(
            @ApiParam(value = "You can enter number of requested common words")
            @RequestParam(defaultValue = NUMBER_OF_WORDS) int numberOfWords) {
        return wordService.getMostUsedWords(numberOfWords);
    }
}