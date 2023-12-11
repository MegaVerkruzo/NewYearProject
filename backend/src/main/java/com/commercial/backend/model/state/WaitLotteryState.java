package com.commercial.backend.model.state;

import com.commercial.backend.model.game.GameState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.commercial.backend.model.game.GameState.waitLottery;

@Getter
public class WaitLotteryState extends State {
    @Schema(example = "waitLottery")
    private final GameState gameState;
    private final Integer activeGifts;
    private final Integer ticketNumber;
    private final LocalDateTime lotteryTime;

    public WaitLotteryState(Integer activeGifts, Integer ticketNumber, LocalDateTime lotteryTime) {
        // :TODO think about text
        super("Жди розыгрыша!");
        this.gameState = waitLottery;
        this.activeGifts = activeGifts;
        this.ticketNumber = ticketNumber;
        this.lotteryTime = lotteryTime;
    }
}
