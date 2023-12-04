package com.commercial.backend.model;

import java.util.List;

public record GameState(
        List<LetterColor> letters,
        int wordLength,
        int currentLine,
        boolean isEnd,
        boolean isPuttedFeedback,
        String postLink,
        String description,
        ApiException exception,
        int countCorrectAnswersBefore,
        boolean isCorrect
) {
    public static GameState createStateWithException(ApiException exception) {
        return new GameState(null, 0, 0, false, false, null, null, exception, 0, false);
    }
}
