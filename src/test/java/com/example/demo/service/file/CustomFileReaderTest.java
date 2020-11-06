package com.example.demo.service.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;

public class CustomFileReaderTest {


    @Test
    public void readFileOk() {
        String filePath = "test.csv";
        String text = "Hello World\nI am test file\nread me, please!";
        List<String> expected = Arrays.stream(text.split("\n")).collect(Collectors.toList());
        List<String> actual;
        File file = new File(filePath);
        try {
            file.createNewFile();
            PrintWriter writer = new PrintWriter(filePath, "UTF-8");
            writer.println(text);
            writer.close();
            CustomFileReader fileReader = new CustomFileReader();
            actual = fileReader.readFile(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Cant write file", e);
        } finally {
            file.delete();
        }
        Assert.assertEquals(actual.size(), expected.size());
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertEquals(actual.get(i), expected.get(i));
        }
    }
}
