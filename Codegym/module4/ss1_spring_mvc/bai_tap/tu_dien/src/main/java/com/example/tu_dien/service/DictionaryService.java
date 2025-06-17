package com.example.tu_dien.service;

import com.example.tu_dien.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService {
    private final WordRepository wordRepository;

    @Autowired
    public DictionaryService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public String getMeaning(String word) {
        return wordRepository.findMeaning(word);
    }
}