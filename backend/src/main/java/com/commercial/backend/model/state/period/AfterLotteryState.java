package com.commercial.backend.model.state.period;

import com.commercial.backend.model.game.GameState;
import com.commercial.backend.model.state.State;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import static com.commercial.backend.model.game.GameState.afterLottery;

@Getter
public class AfterLotteryState implements State {
    @Schema(example = "afterLottery")
    private final GameState gameState;
    @Schema(example = "Спасибо за участие в розыгрыше, c новым годом!")
    private final String text;
    private final Integer activeGifts;

    public AfterLotteryState(Integer activeGifts) {
        // :TODO think about text
        this.text = "С новым годом!";
        this.activeGifts = activeGifts;
        this.gameState = afterLottery;
    }
}