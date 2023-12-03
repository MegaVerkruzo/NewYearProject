package com.commercial.backend.model;

import java.util.List;

import static com.commercial.backend.model.ApiException.noUser;

public record GameState(
        List<LetterColor> letters,
        int wordLength,
        int currentLine,
        boolean isEnd,
        boolean isPuttedFeedback,
        String postLink,
        String description,
        ApiException exception,
        int countCorrectAnswersBefore
) {
    public static GameState createNoUserGameState() {
        return new GameState(null, 0, 0, false, false, null, null, noUser, 0);
    }
}
