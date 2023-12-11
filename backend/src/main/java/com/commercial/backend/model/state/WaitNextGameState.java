package com.commercial.backend.model.state;

import com.commercial.backend.model.game.GameState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import static com.commercial.backend.model.game.GameState.waitNextGame;

@Getter
public class WaitNextGameState extends State {
    @Schema(example = "waitNextGame")
    private final GameState gameState;
    private final Integer activeGifts;

    public WaitNextGameState(Integer activeGifts) {
        // :TODO think about text
        super("Молодец - ты хорошо потрудился!");
        this.gameState = waitNextGame;
        this.activeGifts = activeGifts;
    }
}
