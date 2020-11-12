package com.example.demo.service.parser;

import java.util.List;

public interface FileParser<T> {
    List<T> parse(String filePath);
}
