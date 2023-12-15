package com.commercial.backend.service;

import com.commercial.backend.db.AttemptRepository;
import com.commercial.backend.db.FeedbackRepository;
import com.commercial.backend.db.UserRepository;
import com.commercial.backend.db.entities.Attempt;
import com.commercial.backend.db.entities.Feedback;
import com.commercial.backend.db.entities.Task;
import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.game.Color;
import com.commercial.backend.model.game.LetterColor;
import com.commercial.backend.model.state.State;
import com.commercial.backend.model.state.period.AfterLotteryState;
import com.commercial.backend.model.state.period.BeforeGameState;
import com.commercial.backend.model.state.period.InGameState;
import com.commercial.backend.model.state.period.WaitFeedbackState;
import com.commercial.backend.model.state.period.WaitLotteryState;
import com.commercial.backend.model.state.period.WaitNextGameState;
import com.commercial.backend.security.exception.BadRequestException;
import com.commercial.backend.security.exception.NoWordInDictionaryException;
import com.commercial.backend.security.exception.NotRegisteredException;
import com.commercial.backend.security.exception.NotValidException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.commercial.backend.service.CommonService.getWordInUTF8;

@Service
@AllArgsConstructor
public class AttemptService {

    private final Logger logger = LoggerFactory.getLogger(AttemptService.class);

    private final TaskService taskService;
    private final WordService wordService;
    private final AttemptRepository attemptRepository;
    private final CommonService commonService;
    private final ConfigService configService;
    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;

    private List<LetterColor> compare(String answer, String word) {
        answer = getWordInUTF8(answer);
        word = getWordInUTF8(word);
        logger.info("comparing two strings: " + answer + " and " + word);

        List<Integer> usedLettersInAnswer = new ArrayList<>(answer.length());
        List<Integer> usedLettersInWord = new ArrayList<>(word.length());
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == answer.charAt(i)) {
                usedLettersInAnswer.add(0); // 0 - used, 1 - open
                usedLettersInWord.add(0); // 0 - green, 1 - yellow, 2 - grey
            } else {
                usedLettersInAnswer.add(1);
                usedLettersInWord.add(2);
            }
        }
        logger.info("usedLettersInWord: " + usedLettersInWord);

        logger.info("starting to compare letters");
        List<LetterColor> result = new ArrayList<>();
        for (int i = 0; i < word.length(); ++i) {
            logger.info("i is " + i + " comparing " + answer.charAt(i) + " and " + word.charAt(i));
            if (usedLettersInWord.get(i) == 0) {
                LetterColor currentLetter = new LetterColor(word.charAt(i), Color.green);

                logger.info("Add letter " + currentLetter.letter() + " with state " + currentLetter.state());

                result.add(currentLetter);
                continue;
            }

            Color state = Color.grey;
            for (int j = 0; j < answer.length(); ++j) {
                if (usedLettersInAnswer.get(j) == 0) {
                    continue;
                }

                if (word.charAt(i) == answer.charAt(j)) {
                    usedLettersInAnswer.set(j, 0);
                    state = Color.yellow;
                    break;
                }
            }

            LetterColor currentLetter = new LetterColor(word.charAt(i), state);
            logger.info("Add letter " + currentLetter.letter() + " with state " + currentLetter.state());

            result.add(currentLetter);
        }
        return result;
    }

    public State getState(User user) {
        if (user == null) {
            throw new NotRegisteredException();
        }

        OffsetDateTime now = OffsetDateTime.now();
        logger.info("current millis: " + now);

        // After lottery
        if (configService.isFinishLottery()) {
            return new AfterLotteryState(user.getActiveGifts());
        }

        // Before game
        if (now.isBefore(configService.getStartDate())) {
            return new BeforeGameState();
        }

        Optional<Task> optionalTask = taskService.findPreviousAnswer(now);
        if (optionalTask.isEmpty()) {
            return new WaitLotteryState(
                    user.getActiveGifts(),
                    user.getTicketNumber(),
                    configService.getLotteryDate()
            );
        }

        Task task = optionalTask.get();
        logger.info("answer is " + task);

        List<Attempt> currentAttempts = attemptsInTask(user, task);
        List<LetterColor> letters = getLetters(currentAttempts, task);
        boolean isExistWord = isExistWord(currentAttempts, task.getWord());

        // waitFeedback
        // Logic with feedback field. See addNewWord where set response = null
        Optional<Feedback> feedback = feedbackRepository
                .findFeedbackByUserId(user.getId())
                .stream()
                .filter(fb -> fb.getResponse() == null)
                .findAny();

        if (feedback.isPresent() && (!Objects.equals(feedback.get().getTaskId(), task.getId())
                || currentAttempts.size() >= configService.getTasksCount()
                || isExistWord
        )) {
            return new WaitFeedbackState(
                    letters,
                    task.getWord().length(),
                    user.getActiveGifts()
            );
        }

        // WaitLottery
        if (now.isAfter(commonService.getTasksEndTime())) {
            return new WaitLotteryState(
                    user.getActiveGifts(),
                    user.getTicketNumber(),
                    configService.getLotteryDate()
            );
        }

        // WaitNextGame
        if (isExistWord || currentAttempts.size() >= configService.getTasksCount()) {
            return new WaitNextGameState(user.getActiveGifts());
        }

        // InGame
        return new InGameState(
                letters,
                task.getWord().length(),
                currentAttempts.size(),
                user.getActiveGifts()
        );
    }

    public State addNewWord(
            User user,
            Task task,
            String word,
            OffsetDateTime offsetDateTime
    ) throws NotValidException, BadRequestException {
        if (word == null) {
            throw new NotValidException();
        }
        word = getWordInUTF8(word);
        if (!wordService.isWordExists(word)) {
            throw new NoWordInDictionaryException();
        }

        List<Attempt> currentAttempts = attemptsInTask(user, task);
        if (isExistWord(currentAttempts, task.getWord()) || currentAttempts.size() >= configService.getTasksCount()) {
            throw new BadRequestException();
        }

        // If no feedback, then make
        if (currentAttempts.isEmpty()) {
            feedbackRepository.save(new Feedback(user.getId(), task.getId()));
        }
        attemptRepository.save(new Attempt(user.getId(), word, offsetDateTime));
        if (word.equals(task.getWord())) {
            userRepository.updateActiveGifts(user.getActiveGifts() + 1L, user.getId());
        }

        return getState(user);
    }

    private List<LetterColor> getLetters(List<Attempt> attempts, Task task) {
        return attempts
                .stream()
                .map(attempt -> compare(task.getWord(), attempt.getWord()))
                .flatMap(List::stream)
                .toList();
    }

    private List<Attempt> attemptsInTask(User user, Task task) {
        return attemptRepository
                .findAllByUserIdOrderByDate(user.getId())
                .stream()
                .filter(attempt -> commonService.isAttemptInTask(task, attempt))
                .toList();
    }

    private boolean isExistWord(List<Attempt> attempts, String word) throws BadRequestException {
        for (Attempt attempt : attempts) {
            if (attempt.getWord().equals(word)) {
                return true;
            }
        }
        return false;
    }
}
