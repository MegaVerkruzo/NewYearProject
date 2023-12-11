package com.commercial.backend.model.state.period;

import com.commercial.backend.model.game.GameState;
import com.commercial.backend.model.game.LetterColor;
import com.commercial.backend.model.state.State;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

import static com.commercial.backend.model.game.GameState.waitFeedback;

@Getter
public class WaitFeedbackState implements State {
    @Schema(example = "waitFeedback")
    private final GameState gameState;
    @Schema(example = "Угадай слово, какое слово стоит в обозначении радуги после \"сидит\"")
    private final String question;
    @Schema(example = "Укажите отзыв!")
    private final String text;
    private final List<LetterColor> letters;
    @Schema(example = "Благополучие - это то, что мы сделали вместе за этот год для нас всех")
    private final String description;
    private final Integer wordLength;
    private final Integer activeGifts;

    public WaitFeedbackState(List<LetterColor> letters, String description, Integer wordLength, Integer activeGifts) {
        // :TODO think about text
        this.question = "Угадай слово, какое слово стоит в обозначении радуги после \"сидит\"";
        this.text = "Укажите отзыв!";
        this.gameState = waitFeedback;
        this.letters = letters;
        this.description = description;
        this.wordLength = wordLength;
        this.activeGifts = activeGifts;
    }
}
