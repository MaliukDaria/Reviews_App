package com.example.demo.service.mapper;

import com.example.demo.model.Review;
import com.example.demo.model.ReviewDto;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReviewMapperTest {
    private static ReviewDto expectedFullReviewDto;
    private static ReviewDto expectedReviewDtoWithMissingFields;
    private static ReviewDto emptyReviewDto;
    private static ReviewMapper reviewMapper;

    @BeforeClass
    public static void beforeClass() throws Exception {
        emptyReviewDto = new ReviewDto();
        reviewMapper = new ReviewMapper();
        expectedFullReviewDto = new ReviewDto();
        expectedFullReviewDto.setId(2L);
        expectedFullReviewDto.setProductId("B00813GRG4");
        expectedFullReviewDto.setUserId("A1D87F6ZCVE5NK");
        expectedFullReviewDto.setProfileName("dll pa");
        expectedFullReviewDto.setHelpfulnessNumerator(0L);
        expectedFullReviewDto.setHelpfulnessDenominator(0L);
        expectedFullReviewDto.setScore(1L);
        expectedFullReviewDto.setDateTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(
                1346976000), ZoneId.systemDefault()));
        expectedFullReviewDto.setSummary("Not as Advertised");
        expectedFullReviewDto.setText(
                "\"Product arrived labeled as Jumbo Salted Peanuts...the peanuts were actually" +
                        " small sized unsalted. Not sure if this was an error or if the vendor intended to" +
                        " represent the product as \"\"Jumbo\"\".\"");
        expectedReviewDtoWithMissingFields = new ReviewDto();
        expectedReviewDtoWithMissingFields.setId(2L);
        expectedReviewDtoWithMissingFields.setProductId("B00813GRG4");
        expectedReviewDtoWithMissingFields.setUserId("A1D87F6ZCVE5NK");
        expectedReviewDtoWithMissingFields.setProfileName("dll pa");
        expectedReviewDtoWithMissingFields.setHelpfulnessNumerator(0L);
        expectedReviewDtoWithMissingFields.setHelpfulnessDenominator(0L);
        expectedReviewDtoWithMissingFields.setDateTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(
                1346976000), ZoneId.systemDefault()));
        expectedReviewDtoWithMissingFields.setText(
                "\"Product arrived labeled as Jumbo Salted Peanuts...the peanuts were actually" +
                        " small sized unsalted. Not sure if this was an error or if the vendor intended to" +
                        " represent the product as \"\"Jumbo\"\".\"");
    }

    @Test
    public void mapToReviewOk() {
        Review review = reviewMapper.mapToReview(expectedFullReviewDto);
        Assert.assertEquals(expectedFullReviewDto.getDateTime(), review.getDateTime());
        Assert.assertEquals(expectedFullReviewDto.getId(), review.getExternalId());
        Assert.assertEquals(expectedFullReviewDto.getHelpfulnessDenominator(), review.getHelpfulnessDenominator());
        Assert.assertEquals(expectedFullReviewDto.getHelpfulnessNumerator(), review.getHelpfulnessNumerator());
        Assert.assertEquals(expectedFullReviewDto.getScore(), review.getScore());
        Assert.assertEquals(expectedFullReviewDto.getSummary(), review.getSummary());
        Assert.assertEquals(expectedFullReviewDto.getText(), review.getText());
    }

    @Test
    public void mapToReviewWithMissingScoreAndSummaryFields() {
        Review review = reviewMapper.mapToReview(expectedReviewDtoWithMissingFields);
        Assert.assertNull(review.getSummary());
        Assert.assertNull(review.getScore());
        Assert.assertEquals(expectedReviewDtoWithMissingFields.getDateTime(), review.getDateTime());
        Assert.assertEquals(expectedReviewDtoWithMissingFields.getId(), review.getExternalId());
        Assert.assertEquals(expectedReviewDtoWithMissingFields.getHelpfulnessDenominator(), review.getHelpfulnessDenominator());
        Assert.assertEquals(expectedReviewDtoWithMissingFields.getHelpfulnessNumerator(), review.getHelpfulnessNumerator());
        Assert.assertEquals(expectedReviewDtoWithMissingFields.getText(), review.getText());
    }

    @Test
    public void mapEmptyDto() {
        Review actualReview = reviewMapper.mapToReview(emptyReviewDto);
        Review expectedReview = new Review();
        Assert.assertEquals(expectedReview, actualReview);
    }
}
