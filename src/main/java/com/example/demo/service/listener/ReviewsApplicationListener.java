package com.example.demo.service.listener;

import com.example.demo.model.ReviewDto;
import com.example.demo.service.db.DbDataSaver;
import com.example.demo.service.parser.FileParser;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ReviewsApplicationListener implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger LOGGER = Logger.getLogger(ReviewsApplicationListener.class);
    private final FileParser<ReviewDto> parser;
    private final DbDataSaver<ReviewDto> dbDataSaver;

    @Value("${sourceFilePath}")
    private String filePath;

    public ReviewsApplicationListener(FileParser<ReviewDto> parser,
                                      DbDataSaver<ReviewDto> dbDataSaver) {
        this.parser = parser;
        this.dbDataSaver = dbDataSaver;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        LOGGER.info("MyApplicationListener: onApplicationEvent method starts");
        List<ReviewDto> reviewDtoList = parser.parse(filePath);
        dbDataSaver.saveData(reviewDtoList);
    }
}
