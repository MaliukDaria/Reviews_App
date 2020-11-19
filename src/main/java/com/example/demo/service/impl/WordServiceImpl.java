package com.example.demo.service.impl;

import com.example.demo.model.Word;
import com.example.demo.repository.WordRepository;
import com.example.demo.service.WordService;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;

    public WordServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public Word add(Word word) {
        return wordRepository.save(word);
    }

    @Override
    public void addAll(List<Word> words) {
        wordRepository.saveAll(words);
    }

    @Override
    public List<String> getMostUsedWords(int numberOfWords) {
        return wordRepository.getMostUsedWords(PageRequest.of(0, numberOfWords));
    }
}
