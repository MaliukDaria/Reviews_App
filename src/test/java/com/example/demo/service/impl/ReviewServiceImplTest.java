package com.example.demo.service.impl;

import com.example.demo.repository.ReviewRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

public class ReviewServiceImplTest {
    private static ReviewRepository reviewRepository;
    private static ReviewServiceImpl reviewService;
    private static List<String> reviewsTest;

    @BeforeClass
    public static void beforeClass() {
        reviewRepository = Mockito.mock(ReviewRepository.class);
        reviewService = new ReviewServiceImpl(reviewRepository);
        reviewsTest = new ArrayList<>();
        reviewsTest.add("One two Three fouR");
        reviewsTest.add("Two three");
        reviewsTest.add("three four four");
        reviewsTest.add("Four");
    }

    @Test
    public void getMostUsedWordsOk() {
        Mockito.when(reviewRepository.getAllText()).thenReturn(reviewsTest);
        List<String> mostUsedWords = reviewService.getMostUsedWords(4);
        Assert.assertEquals("four", mostUsedWords.get(0));
        Assert.assertEquals("three", mostUsedWords.get(1));
        Assert.assertEquals("two", mostUsedWords.get(2));
        Assert.assertEquals("one", mostUsedWords.get(3));
    }

    @Test
    public void getMostUsedWordsEmptyList() {
        Mockito.when(reviewRepository.getAllText()).thenReturn(new ArrayList<>());
        List<String> mostUsedWords = reviewService.getMostUsedWords(4);
        Assert.assertEquals(0, mostUsedWords.size());
    }

    @Test
    public void getMoreWordsThanPresent() {
        Mockito.when(reviewRepository.getAllText()).thenReturn(reviewsTest);
        List<String> mostUsedWords = reviewService.getMostUsedWords(10);
        Assert.assertEquals(4, mostUsedWords.size());
    }
}
