package com.example.demo.service.mapper;

import com.example.demo.model.ReviewDto;
import com.example.demo.model.User;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserMapperTest {
    private static ReviewDto REVIEW_DTO;
    private static UserMapper userMapper;

    @BeforeClass
    public static void beforeClass() throws Exception {
        userMapper = new UserMapper();
        REVIEW_DTO = new ReviewDto();
        REVIEW_DTO.setId(1L);
        REVIEW_DTO.setProductId("B001E4KFG0");
        REVIEW_DTO.setUserId("A3SGXH7AUHU8GW");
        REVIEW_DTO.setProfileName("delmartian");
        REVIEW_DTO.setHelpfulnessNumerator(1L);
        REVIEW_DTO.setHelpfulnessDenominator(1L);
        REVIEW_DTO.setScore(5L);
        REVIEW_DTO.setDateTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(
                1303862400L), ZoneId.systemDefault()));
        REVIEW_DTO.setSummary("Good Quality Dog Food");
        REVIEW_DTO.setText(
                "I have bought several of the Vitality canned dog food products and have" +
                        " found them all to be of good quality. The product looks more like a stew than " +
                        "a processed meat and it smells better. My Labrador is finicky and she appreciates" +
                        " this product better than  most.");
    }

    @Test
    public void mapToUserOk() {
        User user = userMapper.mapToUser(REVIEW_DTO);
        Assert.assertEquals(user.getExternalId(), REVIEW_DTO.getUserId());
        Assert.assertEquals(user.getProfileName(), REVIEW_DTO.getProfileName());
    }
}
