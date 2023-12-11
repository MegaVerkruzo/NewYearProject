package com.commercial.backend.model.state.period;

import com.commercial.backend.model.game.GameState;
import com.commercial.backend.model.state.State;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import static com.commercial.backend.model.game.GameState.waitNextGame;

@Getter
public class WaitNextGameState implements State {
    @Schema(example = "waitNextGame")
    private final GameState gameState;
    @Schema(example = "Молодец - ты хорошо потрудился! Жди следующей игры")
    private final String text;
    private final Integer activeGifts;

    public WaitNextGameState(Integer activeGifts) {
        // :TODO think about text
        this.text = "Молодец - ты хорошо потрудился! Жди следующей игры";
        this.gameState = waitNextGame;
        this.activeGifts = activeGifts;
    }
}
