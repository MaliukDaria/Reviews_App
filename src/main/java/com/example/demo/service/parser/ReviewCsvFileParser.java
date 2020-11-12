package com.example.demo.service.parser;

import com.example.demo.model.ReviewDto;
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
public class ReviewCsvFileParser implements FileParser<ReviewDto> {
    private static final Logger LOGGER = Logger.getLogger(ReviewCsvFileParser.class);
    private static final String ID = "Id";
    private static final String PRODUCT_ID = "ProductId";
    private static final String USER_ID = "UserId";
    private static final String PROFILE_NAME = "ProfileName";
    private static final String HELPFULNESS_NUMERATOR = "HelpfulnessNumerator";
    private static final String HELPFULNESS_DENOMINATOR = "HelpfulnessDenominator";
    private static final String SCORE = "Score";
    private static final String TIME = "Time";
    private static final String SUMMARY = "Summary";
    private static final String TEXT = "Text";

    @Override
    public List<ReviewDto> parse(String path) {
        LOGGER.info("ReviewCsvFileParser: method parse started");
        List<ReviewDto> list = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(path));
                CSVParser csvParser = new CSVParser(
                        reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())
        ) {
            for (CSVRecord csvRecord : csvParser) {
                list.add(parseToReviewDto(csvRecord));
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + path, e);
        }
        return list;
    }

    private ReviewDto parseToReviewDto(CSVRecord csvRecord) {
        ReviewDto review = new ReviewDto();
        review.setId(Long.valueOf(csvRecord.get(ID)));
        review.setProductId(csvRecord.get(PRODUCT_ID));
        review.setUserId(csvRecord.get(USER_ID));
        review.setProfileName(csvRecord.get(PROFILE_NAME));
        review.setHelpfulnessNumerator(
                Long.valueOf(csvRecord.get(HELPFULNESS_NUMERATOR)));
        review.setHelpfulnessDenominator(
                Long.valueOf(csvRecord.get(HELPFULNESS_DENOMINATOR)));
        review.setScore(Long.valueOf(csvRecord.get(SCORE)));
        review.setDateTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(
                Long.parseLong(csvRecord.get(TIME))), ZoneId.systemDefault()));
        review.setSummary(csvRecord.get(SUMMARY));
        review.setText(csvRecord.get(TEXT));
        return review;
    }
}
