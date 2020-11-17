package com.example.demo.service.mapper;

import com.example.demo.model.ReviewDto;
import com.example.demo.model.User;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserMapperTest {
    private static UserMapper userMapper;
    private static ReviewDto expectedReviewDto;
    private static ReviewDto emptyReviewDto;

    @BeforeClass
    public static void beforeClass() throws Exception {
        emptyReviewDto = new ReviewDto();
        userMapper = new UserMapper();
        expectedReviewDto = new ReviewDto();
        expectedReviewDto.setId(1L);
        expectedReviewDto.setProductId("B001E4KFG0");
        expectedReviewDto.setUserId("A3SGXH7AUHU8GW");
        expectedReviewDto.setProfileName("delmartian");
        expectedReviewDto.setHelpfulnessNumerator(1L);
        expectedReviewDto.setHelpfulnessDenominator(1L);
        expectedReviewDto.setScore(5L);
        expectedReviewDto.setDateTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(
                1303862400L), ZoneId.systemDefault()));
        expectedReviewDto.setSummary("Good Quality Dog Food");
        expectedReviewDto.setText(
                "I have bought several of the Vitality canned dog food products and have" +
                        " found them all to be of good quality. The product looks more like a stew than " +
                        "a processed meat and it smells better. My Labrador is finicky and she appreciates" +
                        " this product better than  most.");
    }

    @Test
    public void mapToUserOk() {
        User user = userMapper.mapToUser(expectedReviewDto);
        Assert.assertEquals(expectedReviewDto.getUserId(), user.getExternalId());
        Assert.assertEquals(expectedReviewDto.getProfileName(), user.getProfileName());
    }

    @Test
    public void mapEmptyReviewDto() {
        User actualUser = userMapper.mapToUser(emptyReviewDto);
        User expectedUser = new User();
        Assert.assertEquals(expectedUser, actualUser);
    }
}
