package com.commercial.backend.model.game;

import com.commercial.backend.model.ApiException;

import java.util.List;

public record GameStateKlass(
        GameState state,
        String textBeforeGame,
        String textTask,
        int activeGifts,
        int lotteryTicket,
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
    public static GameStateKlass createEmptyState() {
        return new GameStateKlass(null, null, null, 0, 0, null, 0, 0, false, false, null, null, null, 0, false);
    }

    public static GameStateKlass createStateWithException(ApiException exception) {
        return new GameStateKlass(null, null, null, 0, 0, null, 0, 0, false, false, null, null, exception, 0, false);
    }
}
