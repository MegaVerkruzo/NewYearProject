package com.commercial.backend.service;

import com.commercial.backend.db.AttemptRepository;
import com.commercial.backend.db.FeedbackRepository;
import com.commercial.backend.db.TaskRepository;
import com.commercial.backend.db.UserRepository;
import com.commercial.backend.db.entities.Attempt;
import com.commercial.backend.db.entities.Feedback;
import com.commercial.backend.db.entities.Task;
import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.game.Color;
import com.commercial.backend.model.game.LetterColor;
import com.commercial.backend.model.json.JsonWord;
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
import com.commercial.backend.security.exception.OldStateException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
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
    private final TaskRepository taskRepository;
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
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss Z");
        System.out.println("now: " + now.format(timeFormatter));

        // After lottery
        if (configService.isFinishLottery()) {
            return new AfterLotteryState(configService.getAfterLotteryMessage(), user.getActiveGifts());
        }

        // Before game
        if (now.isBefore(configService.getStartDate())) {
            return new BeforeGameState(configService.getBeforeGameMessage());
        }

        Optional<Task> optionalTask = taskService.findPreviousAnswer(now);
        Optional<Feedback> feedback = feedbackRepository
                .findFeedbackByUserId(user.getId())
                .stream()
                .filter(fb -> fb.getResponse() == null || fb.getResponse().isBlank())
                .findAny();
        if (feedback.isPresent() && optionalTask.isEmpty()) {
            Task task = taskRepository
                    .findById(feedback.get().getTaskId())
                    .orElseThrow(BadRequestException::new);
            List<Attempt> currentAttempts = attemptsInTask(user, task);
            List<LetterColor> letters = getLetters(currentAttempts, task);

            return new WaitFeedbackState(letters, commonService.getTreeState(user.getActiveGifts()), currentAttempts.size(), task, user.getActiveGifts());
        } else if (feedback.isPresent()) {
            Task task = taskRepository
                    .findById(feedback.get().getTaskId())
                    .orElseThrow(BadRequestException::new);
            if (!Objects.equals(optionalTask.get().getId(), task.getId())) {
                List<Attempt> currentAttempts = attemptsInTask(user, task);
                List<LetterColor> letters = getLetters(currentAttempts, task);

                return new WaitFeedbackState(letters, commonService.getTreeState(user.getActiveGifts()), currentAttempts.size(), task, user.getActiveGifts());
            }
        } else if (optionalTask.isEmpty()) {
            return new WaitLotteryState(
                    user.getActiveGifts(),
                    commonService.getTreeState(user.getActiveGifts()),
                    configService.getLotteryMessage(),
                    user.getTicketNumber(),
                    configService.getLotteryDate(),
                    configService.getLotteryLinkMessage()
            );
        }

        Task task = optionalTask.get();
        logger.info("-------------------------");
        logger.info("now: " + now.format(timeFormatter));
        logger.info("taskQuesion: " + task.getQuestion());
        logger.info("taskId: " + task.getId());
        logger.info("taskWord: " + task.getQuestion());
        logger.info("answer is " + task);
        logger.info("-------------------------");

        List<Attempt> currentAttempts = attemptsInTask(user, task);
        List<LetterColor> letters = getLetters(currentAttempts, task);
        boolean isExistWord = isExistWord(currentAttempts, task.getWord().toLowerCase());

        // waitFeedback
        // Logic with feedback field. See addNewWord where set response = null
        if (feedback.isPresent() && (!Objects.equals(feedback.get().getTaskId(), task.getId())
                || currentAttempts.size() >= configService.getTasksCount()
                || isExistWord
        )) {
            return new WaitFeedbackState(letters, commonService.getTreeState(user.getActiveGifts()), currentAttempts.size(), task, user.getActiveGifts());
        }

        // WaitLottery
        if (now.isAfter(commonService.getTasksEndTime()) || Objects.equals(task.getId(), configService.getTasksCount()) &&
                (isExistWord || currentAttempts.size() >= configService.getTasksCount())
        ) {
            return new WaitLotteryState(
                    user.getActiveGifts(),
                    commonService.getTreeState(user.getActiveGifts()),
                    configService.getLotteryMessage(),
                    user.getTicketNumber(),
                    configService.getLotteryDate(),
                    configService.getLotteryLinkMessage()
            );
        }

        // WaitNextGame
        if (isExistWord || currentAttempts.size() >= configService.getTasksCount()) {
            return new WaitNextGameState(configService.getWaitNextGameMessage(), commonService.getTreeState(user.getActiveGifts()), user.getActiveGifts());
        }

        // InGame
        return new InGameState(
                letters,
                commonService.getTreeState(user.getActiveGifts()),
                task.getQuestion(),
                task.getWord().length(),
                currentAttempts.size(),
                user.getActiveGifts()
        );
    }

    public State addNewWord(User user, JsonWord jsonWord) throws NotValidException, BadRequestException {
        if (jsonWord == null) {
            throw new OldStateException();
        }
        String word = jsonWord.getWord().toLowerCase();
        OffsetDateTime offsetDateTime = OffsetDateTime.now();

        // :TODO thing about this part of code
        Task task = taskService.findPreviousAnswer(offsetDateTime).orElseThrow(NotValidException::new);
        logger.info("answer: " + task);

        if (task.getWord().length() != word.length()) {
            throw new OldStateException();
        }

        word = getWordInUTF8(word);
        logger.info("Word: " + word);
        if (!wordService.isWordExists(word)) {
            logger.info("Word is no in dictionary");
            throw new NoWordInDictionaryException();
        }

        List<Attempt> currentAttempts = attemptsInTask(user, task);
        logger.info("Current attempts: " + currentAttempts.toString());
        if (isExistWord(currentAttempts, task.getWord().toLowerCase()) || currentAttempts.size() >= configService.getTasksCount()) {
            throw new BadRequestException();
        }

        // If no feedback, then make
        if (currentAttempts.isEmpty()) {
            feedbackRepository.save(new Feedback(user.getId(), task.getId()));
        }
        attemptRepository.save(new Attempt(user.getId(), word, offsetDateTime));
        if (word.equalsIgnoreCase(task.getWord())) {
            userRepository.updateUsersById(user.getActiveGifts() + 1L, user.getId());
        }

        return new State();
    }

    private List<LetterColor> getLetters(List<Attempt> attempts, Task task) {
        return attempts
                .stream()
                .map(attempt -> compare(task.getWord().toLowerCase(), attempt.getWord()))
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
