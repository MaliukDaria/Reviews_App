package com.example.demo.service;

import com.example.demo.model.Word;
import java.util.List;

public interface WordService extends GenericService<Word> {
    List<String> getMostUsedWords(int numberOfWords);
}
