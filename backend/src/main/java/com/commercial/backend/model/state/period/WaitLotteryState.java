package com.commercial.backend.model.state.period;

import com.commercial.backend.model.game.GameState;
import com.commercial.backend.model.state.State;
import com.commercial.backend.service.CommonService;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private final String textWithLink;

    public WaitLotteryState(Integer activeGifts, String waitLotteryText, Integer ticketNumber, OffsetDateTime startLotteryTime, String textWithLink) {
        this.text = waitLotteryText;
        this.activePrizes = CommonService.getActivePrizes(activeGifts);
        this.nonActivePrizes = CommonService.getNonActivePrizes(activeGifts);
        this.gameState = waitEndLottery;
        this.activeGifts = activeGifts;
        this.ticketNumber = ticketNumber;
        this.lotteryTime = startLotteryTime;
        this.textWithLink = textWithLink;
    }
}
