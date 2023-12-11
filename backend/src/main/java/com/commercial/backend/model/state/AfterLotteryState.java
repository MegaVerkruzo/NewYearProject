package com.commercial.backend.model.state;

import com.commercial.backend.model.game.GameState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import static com.commercial.backend.model.game.GameState.afterLottery;

@Getter
public class AfterLotteryState extends State {
    @Schema(example = "afterLottery")
    private final GameState gameState;
    private final Integer activeGifts;

    public AfterLotteryState(Integer activeGifts) {
        // :TODO think about text
        super("С новым годом!");
        this.activeGifts = activeGifts;
        this.gameState = afterLottery;
    }
}
