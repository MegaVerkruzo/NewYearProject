package com.commercial.backend.model.state.period;

import com.commercial.backend.model.game.GameState;
import com.commercial.backend.model.state.State;
import com.commercial.backend.service.CommonService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import static com.commercial.backend.model.game.GameState.waitNextGame;

@Getter
public class WaitNextGameState extends State {
    @Schema(example = "waitNextGame")
    private final GameState gameState;
    @Schema(example = "Молодец - ты хорошо потрудился! Жди следующей игры")
    private final String text;
    @Schema(example = "Вы учавствуете в розыгрыше призов 2-го уровня")
    private final String activePrizes;
    @Schema(example = "Чтобы учавствовать в розыгрыше всех подарков, вам осталось ответить на 3 загадки")
    private final String nonActivePrizes;
    private final Integer activeGifts;

    public WaitNextGameState(String waitNextGameText, Integer activeGifts) {
        this.activePrizes = CommonService.getActivePrizes(activeGifts);
        this.nonActivePrizes = CommonService.getNonActivePrizes(activeGifts);
        this.text = waitNextGameText;
        this.gameState = waitNextGame;
        this.activeGifts = activeGifts;
    }
}
