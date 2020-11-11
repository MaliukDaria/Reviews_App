package com.example.demo.service.parser;

import com.example.demo.model.Review;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ReviewCsvFileParser implements FileParser<Review> {
    private static final Logger LOGGER = Logger.getLogger(ReviewCsvFileParser.class);

    @Override
    public List<Review> parse(String path) {
        List<Review> list = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(path));
                CSVParser csvParser = new CSVParser(
                        reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())
        ) {
            for (CSVRecord csvRecord : csvParser) {
                Review review = new Review();
                review.setId(Long.valueOf(csvRecord.get("Id")));
                review.setProductId(csvRecord.get("ProductId"));
                review.setUserId(csvRecord.get("UserId"));
                review.setProfileName(csvRecord.get("ProfileName"));
                review.setHelpfulnessNumerator(
                        Long.valueOf(csvRecord.get("HelpfulnessNumerator")));
                review.setHelpfulnessDenominator(
                        Long.valueOf(csvRecord.get("HelpfulnessDenominator")));
                review.setScore(Long.valueOf(csvRecord.get("Score")));
                review.setDateTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(
                        Long.parseLong(csvRecord.get("Time"))), ZoneId.systemDefault()));
                review.setSummary(csvRecord.get("Summary"));
                review.setText(csvRecord.get("Text"));
                list.add(review);
            }
        } catch (IOException e) {
            LOGGER.error("Can't read file " + path, e);
            throw new RuntimeException("Can't read file " + path, e);
        }
        return list;
    }
}
