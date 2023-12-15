package com.commercial.backend.service;

import com.commercial.backend.db.TaskRepository;
import com.commercial.backend.db.entities.Task;
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
public class WordService {
    private final Logger logger = LoggerFactory.getLogger(WordService.class);
    // :TODO add method for adding word not only with constructor
    private final Set<String> words;

    public WordService(TaskRepository taskRepository) throws IOException {
        logger.info("Start downloading russian words");
        this.words = getContent();
        taskRepository.findAll().stream().map(Task::getWord).forEach(words::add);
        // :TODO delete this logging
        taskRepository.findAll().forEach(answer -> logger.info(answer.toString()));
        logger.info("End downloading russian words");
    }

    public boolean isWordExists(String word) {
        return words.contains(word);
    }

    private Set<String> getContent() throws IOException {
        try (InputStream is = WordService.class
                .getClassLoader()
                .getResourceAsStream("russian-words.txt")
        ) {
            return Arrays.stream(
                    new String(Objects.requireNonNull(is).readAllBytes(), StandardCharsets.UTF_8).split("\n")
            ).collect(Collectors.toSet());
        }
    }
}
