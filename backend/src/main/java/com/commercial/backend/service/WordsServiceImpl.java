package com.commercial.backend.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class WordsServiceImpl implements WordsService {
    private final Set<String> words;

    public WordsServiceImpl() throws URISyntaxException, IOException {
        this.words = getContent();
    }

    @Override
    public boolean isWordExists(String word) {
        return words.contains(word);
    }

    private Set<String> getContent() throws URISyntaxException, IOException {
        var is = WordsServiceImpl.class.getClassLoader().getResourceAsStream("russian_utf_norm.txt");
        return Arrays.stream(new String(is.readAllBytes(), StandardCharsets.UTF_8).split("\n")).collect(Collectors.toSet());
    }
}
