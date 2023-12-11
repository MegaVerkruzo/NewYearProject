package com.commercial.backend.model.state.period;

import com.commercial.backend.model.game.GameState;
import com.commercial.backend.model.state.State;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.commercial.backend.model.game.GameState.waitEndLottery;

@Getter
public class WaitLotteryState implements State {
    @Schema(example = "waitEndLottery")
    private final GameState gameState;
    @Schema(example = "Розыгрыш будет тогда-то, жди!")
    private final String text;
    private final Integer activeGifts;
    private final Integer ticketNumber;
    private final LocalDateTime lotteryTime;

    public WaitLotteryState(Integer activeGifts, Integer ticketNumber, LocalDateTime startLotteryTime) {
        // :TODO think about text
        this.text = "Розыгрыш будет тогда-то, жди!";
        this.gameState = waitEndLottery;
        this.activeGifts = activeGifts;
        this.ticketNumber = ticketNumber;
        this.lotteryTime = startLotteryTime;
    }
}
