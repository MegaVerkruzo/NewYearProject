package com.commercial.backend.model.state.period;

import com.commercial.backend.model.game.GameState;
import com.commercial.backend.model.game.LetterColor;
import com.commercial.backend.model.state.State;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

import static com.commercial.backend.model.game.GameState.waitNextGame;

@Getter
public class WaitNextGameState implements State {
    @Schema(example = "waitNextGame")
    private final GameState gameState;
    @Schema(example = "Молодец - ты хорошо потрудился! Жди следующей игры")
    private final String text;
    private final List<LetterColor> letters;
    @Schema(example = "Угадай слово, какое слово стоит в обозначении радуги после \"сидит\"")
    private final String question;
    @Schema(example = "Благополучие - это то, что мы сделали вместе за этот год для нас всех")
    private final String description;
    private final Integer wordLength;
    private final Integer activeGifts;

    public WaitNextGameState(List<LetterColor> letters, String description, Integer wordLength, Integer activeGifts) {
        this.question = "Угадай слово, какое слово стоит в обозначении радуги после \"сидит\"";
        this.letters = letters;
        this.description = description;
        this.wordLength = wordLength;
        // :TODO think about text
        this.text = "Молодец - ты хорошо потрудился! Жди следующей игры";
        this.gameState = waitNextGame;
        this.activeGifts = activeGifts;
    }
}
