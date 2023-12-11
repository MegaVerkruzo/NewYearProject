package com.commercial.backend.model.state;

import com.commercial.backend.model.game.GameState;
import com.commercial.backend.model.game.LetterColor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

import static com.commercial.backend.model.game.GameState.waitFeedback;

@Getter
public class WaitFeedbackState extends State {
    @Schema(example = "waitFeedback")
    private final GameState gameState;
    private final List<LetterColor> letters;
    private final String description;
    private final Integer wordLength;
    private final Integer activeGifts;

    public WaitFeedbackState(List<LetterColor> letters, String description, Integer wordLength, Integer activeGifts) {
        // :TODO think about text
        super("Ожидает ответа на отзыв");
        this.gameState = waitFeedback;
        this.letters = letters;
        this.description = description;
        this.wordLength = wordLength;
        this.activeGifts = activeGifts;
    }
}
