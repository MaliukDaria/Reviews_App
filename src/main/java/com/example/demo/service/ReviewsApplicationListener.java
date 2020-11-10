package com.example.demo.service;

import com.example.demo.service.file.CustomFileReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ReviewsApplicationListener implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger LOGGER = Logger.getLogger(ReviewsApplicationListener.class);
    private final CustomFileReader fileReader;

    public ReviewsApplicationListener(CustomFileReader fileReader) {
        this.fileReader = fileReader;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        LOGGER.info("MyApplicationListener: onApplicationEvent method starts");
        Properties fileProps = new Properties();
        try {
            fileProps.load(new FileInputStream("src/main/resources/sourсeFile.properties"));
        } catch (IOException e) {
            LOGGER.error("Cant load sourсeFile.properties file", e);
            throw new RuntimeException("Cant load sourсeFile.properties file", e);
        }
        List<String> fileData =
                fileReader.readFile(fileProps.getProperty("sourceFilePath"));
        System.out.println();
    }
}
