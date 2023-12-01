package com.commercial.backend.service;

import com.commercial.backend.db.AnswersRepository;
import com.commercial.backend.model.Answer;
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
public class WordsServiceImpl implements WordsService {
    private final Logger logger = LoggerFactory.getLogger(WordsServiceImpl.class);
    private final Set<String> words;

    public WordsServiceImpl(AnswersRepository answersRepository) throws IOException {
        logger.info("Start downloading russian words");
        this.words = getContent();
        answersRepository.findAll().stream().map(Answer::getWord).forEach(words::add);
        logger.info("End downloading russian words");
    }

    @Override
    public boolean isWordExists(String word) {
        return words.contains(word);
    }

    private Set<String> getContent() throws IOException {
        InputStream is = WordsServiceImpl.class.getClassLoader().getResourceAsStream("russian_utf_norm.txt");
        return Arrays.stream(
                new String(Objects.requireNonNull(is).readAllBytes(), StandardCharsets.UTF_8).split("\n")
        ).collect(Collectors.toSet());
    }
}
