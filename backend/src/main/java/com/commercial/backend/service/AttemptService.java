package com.commercial.backend.service;

import com.commercial.backend.db.AttemptRepository;
import com.commercial.backend.db.entities.Attempt;
import com.commercial.backend.db.entities.Task;
import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.game.Color;
import com.commercial.backend.model.game.LetterColor;
import com.commercial.backend.model.state.State;
import com.commercial.backend.model.state.period.BeforeGameState;
import com.commercial.backend.model.state.period.InGameState;
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

@Service
@AllArgsConstructor
public class AttemptService {

    private final Logger logger = LoggerFactory.getLogger(AttemptService.class);

    private final TaskService taskService;
    private final WordService wordService;
    private final AttemptRepository attemptRepository;
    private final DeltaService deltaService;

    private List<LetterColor> compare(String answer, String word) {
        answer = DeltaService.getWordInUTF8(answer);
        word = DeltaService.getWordInUTF8(word);
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

    public State getAllInfo(User user) {
        if (user == null) {
            throw new NotRegisteredException();
        }

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        logger.info("current millis: " + offsetDateTime);

        Task task = taskService.findPreviousAnswer(offsetDateTime);
        logger.info("answer is " + task);

        List<Attempt> attempts = attemptRepository.findAllByUserIdOrderByDate(user.getId());
        logger.info("attempts size: " + attempts.size());

        int countCorrectAnswersBefore = taskService.countCorrectAnswers(attempts);
        logger.info("countCorrectAnswersBefore: " + countCorrectAnswersBefore);

        if (task == null) {
            return new BeforeGameState();
//            return new GameStateKlass(
//                    null,
//                    null,
//                    null,
//                    0,
//                    0,
//                    new ArrayList<>(),
//                    0,
//                    0,
//                    true,
//                    // :TODO change logic
//                    false,
//                    null,
//                    null,
//                    null,
//                    countCorrectAnswersBefore,
//                    false
//            );
        }

        List<Attempt> currentAttempts = new ArrayList<>();
        for (Attempt attempt : attempts) {
            if (task.getDate().isBefore(attempt.getDate())
                    && attempt.getDate().isBefore(deltaService.getDeltaUp(task.getDate()))) {
                currentAttempts.add(attempt);
            }
        }

        List<LetterColor> attemptsInfo = new ArrayList<>();
        for (Attempt attempt : currentAttempts) {
            attemptsInfo.addAll(compare(task.getWord(), attempt.getWord()));
        }

        boolean isEnd = currentAttempts.size() == 5 || taskService.countCorrectAnswers(currentAttempts) >= 1;

        // :TODO change logic
        boolean isPuttedFeedback = false && isEnd && offsetDateTime.isAfter(taskService.getMaxDate());

        return new BeforeGameState();
//        return new GameStateKlass(
//                null,
//                null,
//                null,
//                0,
//                0,
//                attemptsInfo,
//                answer.getWord().length(),
//                currentAttempts.size(),
//                isEnd,
//                isPuttedFeedback,
//                null,
//                isEnd ? answer.getDescription() : "",
//                null,
//                countCorrectAnswersBefore,
//                false
//        );
    }

    public State addNewWord(
            User user,
            Task task,
            String word,
            OffsetDateTime offsetDateTime
    ) throws NotValidException {
        if (word == null) {
            throw new NotValidException();
        }
        word = DeltaService.getWordInUTF8(word);

        if (!wordService.isWordExists(word)) {
            throw new NoWordInDictionaryException();
        }

        List<Attempt> attempts = attemptRepository.findAllByUserIdOrderByDate(user.getId());
        List<Attempt> currentAttempts = new ArrayList<>();
        for (Attempt attempt : attempts) {
            if (task.getDate().isBefore(attempt.getDate())
                    && attempt.getDate().isBefore(deltaService.getDeltaUp(task.getDate()))) {
                currentAttempts.add(attempt);
            }
        }

        for (Attempt attempt : currentAttempts) {
            if (attempt.getWord().equals(task.getWord())) {
                throw new BadRequestException();
            }
        }

        if (currentAttempts.size() >= 5) {
            throw new BadRequestException();
        }

        attemptRepository.save(new Attempt(user.getId(), word, offsetDateTime));

        return new InGameState(
                compare(task.getWord(), word),
                0,
                0,
                0
        );
//        return new GameStateKlass(
//                null,
//                null,
//                null,
//                0,
//                0,
//                compare(answer.getWord(), word),
//                0,
//                0,
//                false,
//                (answer.getWord().equals(word) || currentAttempts.size() == 4)
//                        && answer.getDescription().equals("5"),
//                null,
//                answer.getWord().equals(word) ? answer.getDescription() : null,
//                null,
//                0,
//                answer.getWord().equals(word)
//        );
    }
}
