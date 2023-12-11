package com.commercial.backend.service;

import com.commercial.backend.db.AnswerRepository;
import com.commercial.backend.db.entities.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WordsServiceImpl implements IWordsService {
    private final Logger logger = LoggerFactory.getLogger(WordsServiceImpl.class);
    // :TODO add method for adding word not only with constructor
    private final Set<String> words;

    public WordsServiceImpl(AnswerRepository answerRepository) throws IOException {
        logger.info("Start downloading russian words");
        this.words = getContent();
        answerRepository.findAll().stream().map(Answer::getWord).forEach(words::add);
        // :TODO delete this logging
        answerRepository.findAll().forEach(answer -> logger.info(answer.toString()));
        logger.info("End downloading russian words");
    }

    @Override
    public boolean isWordExists(String word) {
        return words.contains(word);
    }

    private Set<String> getContent() throws IOException {
        try (InputStream is = WordsServiceImpl.class
                .getClassLoader()
                .getResourceAsStream("russian_utf_norm.txt")
        ) {
            return Arrays.stream(
                    new String(Objects.requireNonNull(is).readAllBytes(), StandardCharsets.UTF_8).split("\n")
            ).collect(Collectors.toSet());
        }
    }
}
