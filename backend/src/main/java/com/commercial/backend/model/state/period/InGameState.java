package com.commercial.backend.model.state.period;

import com.commercial.backend.model.game.GameState;
import com.commercial.backend.model.game.LetterColor;
import com.commercial.backend.model.state.State;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

import static com.commercial.backend.model.game.GameState.inGame;

@Getter
public class InGameState implements State {
    @Schema(example = "inGame")
    private final GameState gameState;
    @Schema(example = "Угадай слово, какое слово стоит в обозначении радуги после \"сидит\"")
    private final String text;
    @Schema(example = "Вы учавствуете в розыгрыше призов 2-го уровня")
    private final String activePrizes;
    @Schema(example = "Чтобы учавствовать в розыгрыше всех подарков, вам осталось ответить на 3 загадки")
    private final String nonActivePrizes;
    private final List<LetterColor> letters;
    private final Integer wordLength;
    private final Integer currentLine;
    private final Integer activeGifts;

    public InGameState(List<LetterColor> letters, Integer wordLength, Integer currentLine, Integer activeGifts) {
        // :TODO Get text from config
        this.text = "Угадай слово, какое слово стоит в обозначении радуги после \"сидит\"";
        this.activePrizes = "Вы учавствует в розыгрыше таких вещей";
        this.nonActivePrizes = "Чтобы учавствовать в розыгрыше всех подарков, вам осталось ответить на 3 загадки";
        this.letters = letters;
        this.wordLength = wordLength;
        this.currentLine = currentLine;
        this.activeGifts = activeGifts;
        this.gameState = inGame;
    }
}
