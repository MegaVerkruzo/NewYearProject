package com.commercial.backend.model.state;

import com.commercial.backend.model.game.GameState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import static com.commercial.backend.model.game.GameState.beforeGame;

@Getter
public class BeforeGameState extends State {
    @Schema(type = "beforeGame")
    private final GameState gameState;

    public BeforeGameState() {
        // :TODO Get text from config
        super("Первая игра начнётся 19 декабря в 9:00!");
        this.gameState = beforeGame;
    }
}
