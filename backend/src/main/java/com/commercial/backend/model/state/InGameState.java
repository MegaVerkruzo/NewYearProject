package com.commercial.backend.model.state;

import com.commercial.backend.model.game.GameState;
import com.commercial.backend.model.game.LetterColor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

import static com.commercial.backend.model.game.GameState.inGame;

@Getter
public class InGameState extends State {
    @Schema(example = "inGame")
    private final GameState gameState;
    private final List<LetterColor> letters;
    private final Integer wordLength;
    private final Integer currentLine;
    private final Integer activeGifts;

    public InGameState(List<LetterColor> letters, Integer wordLength, Integer currentLine, Integer activeGifts) {
        // :TODO Get text from config
        super("in process");
        this.letters = letters;
        this.wordLength = wordLength;
        this.currentLine = currentLine;
        this.activeGifts = activeGifts;
        this.gameState = inGame;
    }
}
