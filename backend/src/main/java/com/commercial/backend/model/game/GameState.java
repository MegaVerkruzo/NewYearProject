package com.commercial.backend.model.game;

import com.commercial.backend.model.ApiException;

import java.util.List;

public record GameState(
        PeriodState state,
        String text,
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
    public static GameState createEmptyState() {
        return new GameState(null, null, null, 0, 0, false, false, null, null, null, 0, false);
    }

    public static GameState createStateWithException(ApiException exception) {
        return new GameState(null, null, null, 0, 0, false, false, null, null, exception, 0, false);
    }
}
