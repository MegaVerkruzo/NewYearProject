package com.commercial.backend.service;

import com.commercial.backend.db.AttemptsRepository;
import com.commercial.backend.db.entities.Answer;
import com.commercial.backend.db.entities.Attempt;
import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.ApiException;
import com.commercial.backend.model.game.Color;
import com.commercial.backend.model.game.GameState;
import com.commercial.backend.model.game.LetterColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.commercial.backend.Common.getDeltaUp;
import static com.commercial.backend.Common.getWordInUTF8;

@Service
public class AttemptService implements IAttemptService {

    private final Logger logger = LoggerFactory.getLogger(AttemptService.class);

    private final AnswersService answersService;
    private final WordsService wordsService;
    private final AttemptsRepository attemptRepository;

    AttemptService(AnswersService answersService, WordsService wordsService, AttemptsRepository attemptRepository) {
        this.answersService = answersService;
        this.wordsService = wordsService;
        this.attemptRepository = attemptRepository;
    }

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

    @Override
    public GameState getAllInfo(User user) {
        if (user == null) {
            return GameState.createStateWithException(ApiException.noUser);
        }

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        logger.info("current millis: " + offsetDateTime);

        Answer answer = answersService.findPreviousAnswer(offsetDateTime);
        logger.info("answer is " + answer);

        List<Attempt> attempts = attemptRepository.findAllByPhone(user.getPhone());
        logger.info("attempts size: " + attempts.size());

        int countCorrectAnswersBefore = answersService.countCorrectAnswers(attempts);
        logger.info("countCorrectAnswersBefore: " + countCorrectAnswersBefore);

        if (answer == null) {
            return new GameState(
                    null,
                    null,
                    new ArrayList<>(),
                    0,
                    0,
                    true,
                    user.getFeedback() == null,
                    null,
                    null,
                    null,
                    countCorrectAnswersBefore,
                    false
            );
        }

        List<Attempt> currentAttempts = new ArrayList<>();
        for (Attempt attempt : attempts) {
            if (answer.getDate().isBefore(attempt.getDate())
                    && attempt.getDate().isBefore(getDeltaUp(answer.getDate()))) {
                currentAttempts.add(attempt);
            }
        }

        List<LetterColor> attemptsInfo = new ArrayList<>();
        for (Attempt attempt : currentAttempts) {
            attemptsInfo.addAll(compare(answer.getWord(), attempt.getWord()));
        }

        boolean isEnd = currentAttempts.size() == 5 || answersService.countCorrectAnswers(currentAttempts) >= 1;

        boolean isPuttedFeedback = user.getFeedback() == null && isEnd && offsetDateTime.isAfter(answersService.getMaxDate());

        return new GameState(
                null,
                null,
                attemptsInfo,
                answer.getWord().length(),
                currentAttempts.size(),
                isEnd,
                isPuttedFeedback,
                null,
                isEnd ? answer.getDescription() : "",
                null,
                countCorrectAnswersBefore,
                false
        );
    }

    @Override
    public GameState addNewWord(User user, Answer answer, String word, OffsetDateTime offsetDateTime) {
        word = getWordInUTF8(word);

        if (!wordsService.isWordExists(word)) {
            return GameState.createStateWithException(ApiException.noWordInDictionary);
        }

        List<Attempt> attempts = attemptRepository.findAllByPhone(user.getPhone());
        List<Attempt> currentAttempts = new ArrayList<>();
        for (Attempt attempt : attempts) {
            if (answer.getDate().isBefore(attempt.getDate())
                    && attempt.getDate().isBefore(getDeltaUp(answer.getDate()))) {
                currentAttempts.add(attempt);
            }
        }

        for (Attempt attempt : currentAttempts) {
            if (attempt.getWord().equals(answer.getWord())) {
                return GameState.createStateWithException(ApiException.alreadyExistCorrectAttempt);
            }
        }

        if (currentAttempts.size() >= 5) {
            return GameState.createStateWithException(ApiException.alreadyExist5Attempts);
        }

        attemptRepository.insert(new Attempt(user.getPhone(), word, offsetDateTime));

        return new GameState(
                null,
                null,
                compare(answer.getWord(), word),
                0,
                0,
                false,
                (answer.getWord().equals(word) || currentAttempts.size() == 4)
                        && answer.getDescription().equals("5"),
                null,
                answer.getWord().equals(word) ? answer.getDescription() : null,
                null,
                0,
                answer.getWord().equals(word)
        );
    }
}
