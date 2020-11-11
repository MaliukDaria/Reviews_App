package com.example.demo.service;

import com.example.demo.model.Review;
import com.example.demo.service.parser.FileParser;
import com.example.demo.service.parser.ReviewCsvFileParser;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ReviewsApplicationListener implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger LOGGER = Logger.getLogger(ReviewsApplicationListener.class);
    @Value("${sourceFilePath}")
    private String filePath;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        LOGGER.info("MyApplicationListener: onApplicationEvent method starts");
        FileParser<Review> parser = new ReviewCsvFileParser();
        List<Review> reviewList = parser.parse(filePath);
    }
}
