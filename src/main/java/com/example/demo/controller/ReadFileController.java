package com.example.demo.controller;

import com.example.demo.service.file.CustomFileReader;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class ReadFileController {
    private final CustomFileReader fileReader;

    public ReadFileController(CustomFileReader fileReader) {
        this.fileReader = fileReader;
    }

    @PostMapping
    public String readFile(@RequestParam String filePath) {
        List<String> fileData = fileReader.readFile(filePath);
        return "File read successfully";
    }
}
