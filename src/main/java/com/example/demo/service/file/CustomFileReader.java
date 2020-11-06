package com.example.demo.service.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class CustomFileReader {
    private static final Logger LOGGER = Logger.getLogger(CustomFileReader.class);

    public List<String> readFile(String path) {
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while (br.ready()) {
                result.add(br.readLine());
            }
        } catch (IOException e) {
            LOGGER.error("Can`t read file", e);
        }
        return result;
    }
}
