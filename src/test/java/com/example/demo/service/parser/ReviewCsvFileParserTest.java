package com.example.demo.service.parser;

import com.example.demo.model.ReviewDto;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReviewCsvFileParserTest {
    private static final String FILE_PATH = "src/test/java/resources/test.csv";
    private static final String EMPTY_FILE_PATH = "src/test/java/resources/emptyTest.csv";
    private FileParser<ReviewDto> fileParser;

    @Before
    public void setUp() throws Exception {
        fileParser = new ReviewCsvFileParser();
    }

    @Test
    public void ReviewCsvParseOk() {
        ReviewDto review = new ReviewDto();
        review.setId(1L);
        review.setProductId("B001E4KFG0");
        review.setUserId("A3SGXH7AUHU8GW");
        review.setProfileName("delmartian");
        review.setHelpfulnessNumerator(1L);
        review.setHelpfulnessDenominator(1L);
        review.setScore(5L);
        review.setDateTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(
                1303862400L), ZoneId.systemDefault()));
        review.setSummary("Good Quality Dog Food");
        review.setText(
                "I have bought several of the Vitality canned dog food products and have" +
                        " found them all to be of good quality. The product looks more like a stew than " +
                        "a processed meat and it smells better. My Labrador is finicky and she appreciates" +
                        " this product better than  most.");
        List<ReviewDto> reviewList = fileParser.parse(FILE_PATH);
        Assert.assertEquals(reviewList.size(), 1);
        Assert.assertEquals(reviewList.get(0), review);
    }

    @Test
    public void ParseEmptyFileOk() {
        List<ReviewDto> reviewList;
        File file = new File(EMPTY_FILE_PATH);
        try {
            file.createNewFile();
            reviewList = fileParser.parse(EMPTY_FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException("Cant create file", e);
        } finally {
            file.delete();
        }
        Assert.assertEquals(reviewList.size(), 0);
    }

    @Test(expected = RuntimeException.class)
    public void NonExistingFileOk() {
        List<ReviewDto> reviewList = fileParser.parse(EMPTY_FILE_PATH);
    }
}
