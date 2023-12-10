package com.commercial.backend.service;

import com.commercial.backend.db.AttemptsRepository;
import com.commercial.backend.model.Answer;
import com.commercial.backend.model.Attempt;
import com.commercial.backend.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.*;

import static com.commercial.backend.Common.*;

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

    // :APPROVED
    private List<Map<String, Object>> compare(String answer, String word) {
        answer = getWordInUTF8(answer);
        word = getWordInUTF8(word);
        logger.info("comparing two strings: " + answer + " and " + word);

        List<Map<String, Object>> result = new ArrayList<>();
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
        for (int i = 0; i < word.length(); ++i) {
            logger.info("i is " + i + " comparing " + answer.charAt(i) + " and " + word.charAt(i));
            Map<String, Object> currentLetter = new HashMap<>();
            if (usedLettersInWord.get(i) == 0) {
                currentLetter.put("letter", word.charAt(i));
                currentLetter.put("state", "green");

                logger.info("Add letter " + currentLetter.get("letter") + " with state " + currentLetter.get("state"));

                result.add(currentLetter);
                continue;
            }

            String state = "grey";
            for (int j = 0; j < answer.length(); ++j) {
                if (usedLettersInAnswer.get(j) == 0) {
                    continue;
                }

                if (word.charAt(i) == answer.charAt(j)) {
                    usedLettersInAnswer.set(j, 0);
                    state = "yellow";
                    break;
                }
            }

            currentLetter.put("letter", word.charAt(i));
            currentLetter.put("state", state);
            logger.info("Add letter " + currentLetter.get("letter") + " with state " + currentLetter.get("state"));

            result.add(currentLetter);
        }
        return result;
    }

    // :APPROVED
    @Override
    public Map<String, Object> getAllInfo(User user) {
        if (user == null) {
            return pairToMap("exception", "noUser");
        }

        Map<String, Object> result = new HashMap<>();

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        logger.info("current millis: " + offsetDateTime);

        Answer answer = answersService.findPreviousAnswer(offsetDateTime);
        logger.info("answer is " + answer);

        List<Attempt> attempts = attemptRepository.findAllByPhone(user.getPhone());
        logger.info("attempts size: " + attempts.size());

        int countCorrectAnswersBefore = answersService.countCorrectAnswers(attempts);
        logger.info("countCorrectAnswersBefore: " + countCorrectAnswersBefore);

        if (answer == null) {
            result.put("countCorrectAnswersBefore", countCorrectAnswersBefore);
            result.put("letters", new ArrayList<>());
            result.put("wordLength", 0);
            result.put("currentLine", 0);
            result.put("isEnd", true);
            result.put("isPuttedFeedback", user.getFeedback() == null);
            result.put("description", "");
            result.put("exception", "");
            return result;
        }

        List<Attempt> currentAttempts = new ArrayList<>();
        for (Attempt attempt : attempts) {
            if (answer.getDate().isBefore(attempt.getDate())
                    && attempt.getDate().isBefore(getDeltaUp(answer.getDate()))) {
                currentAttempts.add(attempt);
            }
        }

        List<Map<String, Object>> attemptsInfo = new ArrayList<>();
        for (Attempt attempt : currentAttempts) {
            attemptsInfo.addAll(compare(answer.getWord(), attempt.getWord()));
        }

        boolean isEnd = false;
        if (currentAttempts.size() == 5 || answersService.countCorrectAnswers(currentAttempts) >= 1) {
            isEnd = true;
        }

        boolean isPuttedFeedback = false;
        if (user.getFeedback() == null && isEnd && offsetDateTime.isAfter(answersService.getMaxDate())) {
            isPuttedFeedback = true;
        }

        result.put("countCorrectAnswersBefore", countCorrectAnswersBefore);
        result.put("letters", attemptsInfo);
        result.put("wordLength", answer.getWord().length());
        result.put("currentLine", currentAttempts.size());
        result.put("isEnd", isEnd);
        result.put("isPuttedFeedback", isPuttedFeedback);
        result.put("description", isEnd ? answer.getDescription() : "");
        result.put("exception", "");
        return result;
    }

    // :APPROVED
    @Override
    public Map<String, Object> addNewWord(User user, Answer answer, String word, OffsetDateTime offsetDateTime) {
        word = getWordInUTF8(word);

        if (!wordsService.isWordExists(word)) {
            return pairToMap("exception", "noWordInDictionary");
        }

        Map<String, Object> result = new HashMap<>();

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
                return pairToMap("exception", "alreadyExistCorrectAttempt");
            }
        }

        if (currentAttempts.size() >= 5) {
            return pairToMap("exception", "alreadyExist5Attempts");
        }

        attemptRepository.insert(new Attempt(user.getPhone(), word, offsetDateTime));

        result.put("letters", compare(answer.getWord(), word));
        result.put("isCorrect", answer.getWord().equals(word));
        if (answer.getWord().equals(word) || currentAttempts.size() == 4) {
            result.put("isPuttedFeedback", answer.getDescription().equals("5"));
        }
        if (answer.getWord().equals(word)) {
            result.put("description", answer.getDescription());
        }
        result.put("exception", "");
        return result;
    }
}
