package com.example.demo.service;

import com.example.demo.service.file.CustomFileReader;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ReviewsApplicationListener implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger LOGGER = Logger.getLogger(ReviewsApplicationListener.class);
    private final CustomFileReader fileReader;
    @Value("${sourceFilePath}")
    private String filePath;

    public ReviewsApplicationListener(CustomFileReader fileReader) {
        this.fileReader = fileReader;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        LOGGER.info("MyApplicationListener: onApplicationEvent method starts");
        List<String> fileData = fileReader.readFile(filePath);
    }
}
