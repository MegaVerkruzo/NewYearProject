package com.commercial.backend.model.state.period;

import com.commercial.backend.db.entities.Task;
import com.commercial.backend.model.game.GameState;
import com.commercial.backend.model.game.LetterColor;
import com.commercial.backend.model.state.State;
import com.commercial.backend.service.CommonService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

import static com.commercial.backend.model.game.GameState.waitFeedback;

@Getter
public class WaitFeedbackState extends State {
    @Schema(example = "waitFeedback")
    private final GameState gameState;
    @Schema(example = "Угадай слово, какое слово стоит в обозначении радуги после \"сидит\"")
    private final String text;
    @Schema(example = "Вы учавствуете в розыгрыше призов 2-го уровня")
    private final String activePrizes;
    @Schema(example = "Чтобы учавствовать в розыгрыше всех подарков, вам осталось ответить на 3 загадки")
    private final String nonActivePrizes;
    @Schema(example = "Укажите отзыв!")
    private final String feedbackQuestion;
    private final String afterFeedbackResponse;
    private final List<LetterColor> letters;
    private final Integer wordLength;
    private final Integer activeGifts;

    public WaitFeedbackState(List<LetterColor> letters, Task task, Integer activeGifts) {
        this.activePrizes = CommonService.getActivePrizes(activeGifts);
        this.nonActivePrizes = CommonService.getNonActivePrizes(activeGifts);
        this.text = task.getQuestion();
        this.feedbackQuestion = task.getFeedbackQuestion();
        this.afterFeedbackResponse = task.getAfterFeedbackResponse();
        this.wordLength = task.getWord().length();
        this.gameState = waitFeedback;
        this.letters = letters;
        this.activeGifts = activeGifts;
    }
}
