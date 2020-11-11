package com.example.demo.service.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FileSystemFileReaderTest {
    private static final String FILE_PATH = "test.csv";
    private static FileSystemFileReader fileReader;

    @BeforeAll
    static void beforeAll() {
        fileReader = new FileSystemFileReader();
    }

    @Test
    public void readFileOk() {
        String text = "Hello World\nI am test file\nread me, please!";
        List<String> expected = Arrays.stream(text.split("\n")).collect(Collectors.toList());
        List<String> actual;
        File file = new File(FILE_PATH);
        try {
            file.createNewFile();
            PrintWriter writer = new PrintWriter(FILE_PATH, "UTF-8");
            writer.println(text);
            writer.close();
            actual = fileReader.readFile(FILE_PATH);
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

    @Test
    public void readEmptyFile() {
        List<String> actual;
        File file = new File(FILE_PATH);
        try {
            file.createNewFile();
            actual = fileReader.readFile(FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException("Cant write file", e);
        } finally {
            file.delete();
        }
        Assert.assertEquals(actual.size(), 0);
    }

    @Test
    public void readNonExistentFile() {
        Assertions.assertThrows(
                RuntimeException.class, () -> fileReader.readFile(FILE_PATH));
    }
}
