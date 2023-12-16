package com.commercial.backend.model.state.period;

import com.commercial.backend.model.game.GameState;
import com.commercial.backend.model.state.State;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.OffsetDateTime;

import static com.commercial.backend.model.game.GameState.waitEndLottery;

@Getter
public class WaitLotteryState extends State {
    @Schema(example = "waitEndLottery")
    private final GameState gameState;
    @Schema(example = "Розыгрыш будет тогда-то, жди!")
    private final String text;
    @Schema(example = "Вы учавствуете в розыгрыше призов 2-го уровня")
    private final String activePrizes;
    @Schema(example = "Чтобы учавствовать в розыгрыше всех подарков, вам осталось ответить на 3 загадки")
    private final String nonActivePrizes;
    private final Integer activeGifts;
    private final Integer ticketNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private final OffsetDateTime lotteryTime;

    public WaitLotteryState(Integer activeGifts, Integer ticketNumber, OffsetDateTime startLotteryTime) {
        // :TODO think about text
        this.text = "Розыгрыш будет тогда-то, жди!";
        this.activePrizes = "Вы учавствует в розыгрыше таких вещей";
        this.nonActivePrizes = "Чтобы учавствовать в розыгрыше всех подарков, вам осталось ответить на 3 загадки";
        this.gameState = waitEndLottery;
        this.activeGifts = activeGifts;
        this.ticketNumber = ticketNumber;
        this.lotteryTime = startLotteryTime;
    }
}
