package com.example.demo.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ReviewDto {
    private Long id;
    private String productId;
    private String userId;
    private String profileName;
    private Long helpfulnessNumerator;
    private Long helpfulnessDenominator;
    private Long score;
    private LocalDateTime dateTime;
    private String summary;
    private String text;
}
