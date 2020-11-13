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
    private static ReviewDto REVIEW_DTO;
    private static ReviewMapper reviewMapper;

    @BeforeClass
    public static void beforeClass() throws Exception {
        reviewMapper = new ReviewMapper();
        REVIEW_DTO = new ReviewDto();
        REVIEW_DTO.setId(2L);
        REVIEW_DTO.setProductId("B00813GRG4");
        REVIEW_DTO.setUserId("A1D87F6ZCVE5NK");
        REVIEW_DTO.setProfileName("dll pa");
        REVIEW_DTO.setHelpfulnessNumerator(0L);
        REVIEW_DTO.setHelpfulnessDenominator(0L);
        REVIEW_DTO.setScore(1L);
        REVIEW_DTO.setDateTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(
                1346976000), ZoneId.systemDefault()));
        REVIEW_DTO.setSummary("Not as Advertised");
        REVIEW_DTO.setText(
                "\"Product arrived labeled as Jumbo Salted Peanuts...the peanuts were actually" +
                        " small sized unsalted. Not sure if this was an error or if the vendor intended to" +
                        " represent the product as \"\"Jumbo\"\".\"");
    }

    @Test
    public void mapToReviewOk() {
        Review review = reviewMapper.mapToReview(REVIEW_DTO);
        Assert.assertEquals(review.getDateTime(), REVIEW_DTO.getDateTime());
        Assert.assertEquals(review.getExternalId(), REVIEW_DTO.getId());
        Assert.assertEquals(review.getHelpfulnessDenominator(), REVIEW_DTO.getHelpfulnessDenominator());
        Assert.assertEquals(review.getHelpfulnessNumerator(), REVIEW_DTO.getHelpfulnessNumerator());
        Assert.assertEquals(review.getScore(), REVIEW_DTO.getScore());
        Assert.assertEquals(review.getSummary(), REVIEW_DTO.getSummary());
        Assert.assertEquals(review.getText(), REVIEW_DTO.getText());
    }
}
