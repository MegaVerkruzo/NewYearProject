package com.commercial.backend.model.state.period;

import com.commercial.backend.model.game.GameState;
import com.commercial.backend.model.state.State;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import static com.commercial.backend.model.game.GameState.beforeGame;

@Getter
public class BeforeGameState extends State {
    @Schema(type = "beforeGame")
    private final GameState gameState;
    @Schema(example = "Первая игра начнётся 19 декабря в 9:00!")
    private final String text;

    public BeforeGameState(String message) {
        this.text = message;
        this.gameState = beforeGame;
    }
}
