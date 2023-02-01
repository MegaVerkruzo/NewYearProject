package com.commercial.backend.service;

import com.commercial.backend.db.RussianWordsRepository;
import com.commercial.backend.model.RussianWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.commercial.backend.Common.getWordInUTF8;

@Service
public class WordsServiceImpl implements WordsService {
    private RussianWordsRepository repository;

    public WordsServiceImpl(RussianWordsRepository repository) {
        this.repository = repository;
    }
    @Override
    public boolean isWordExists(String word) {
        return repository.isWordExists(word);
    }
}
