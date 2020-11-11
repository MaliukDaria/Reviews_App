package com.example.demo.service.parser;

import com.example.demo.model.Review;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReviewCsvFileParserTest {
    private static final String FILE_PATH = "src/test/java/resources/test.csv";
    private static final String EMPTY_FILE_PATH = "src/test/java/resources/emptyTest.csv";
    private static FileParser<Review> fileParser;

    @BeforeClass
    public static void beforeClass() {
        fileParser = new ReviewCsvFileParser();
    }

    @Test
    public void ReviewCsvParseOk() {
        Review review = new Review();
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
        List<Review> reviewList = fileParser.parse(FILE_PATH);
        Assert.assertEquals(reviewList.get(0), review);
    }

    @Test
    public void ParseEmptyFileOk() {
        List<Review> reviewList;
        File file = new File(EMPTY_FILE_PATH);
        try {
            file.createNewFile();
            reviewList = fileParser.parse(EMPTY_FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException("Cant write file", e);
        } finally {
            file.delete();
        }
        Assert.assertEquals(reviewList.size(), 0);
    }
}
