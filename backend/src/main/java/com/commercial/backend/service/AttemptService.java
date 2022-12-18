package com.commercial.backend.service;

import com.commercial.backend.db.AnswersRepository;
import com.commercial.backend.db.AttemptsRepository;
import com.commercial.backend.db.RussianWordsRepository;
import com.commercial.backend.db.UsersRepository;
import com.commercial.backend.model.Answer;
import com.commercial.backend.model.Attempt;
import com.commercial.backend.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AttemptService implements IAttemptService {

    private final int FIRST_DAY = 19;
    private final int LAST_DAY = 23;

    private final Logger logger = LoggerFactory.getLogger(AttemptService.class);

    private final AnswersRepository answersRepository;
    private final UsersRepository usersRepository;
    private final AttemptsRepository attemptRepository;
    private final RussianWordsRepository russianWordsRepository;

    AttemptService(AnswersRepository answersRepository, UsersRepository usersRepository, AttemptsRepository attemptRepository, RussianWordsRepository russianWordsRepository) {
        this.answersRepository = answersRepository;
        this.usersRepository = usersRepository;
        this.attemptRepository = attemptRepository;
        this.russianWordsRepository = russianWordsRepository;
    }

    private int getDayOfMonth() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        logger.info("Day of month: " + calendar.get(Calendar.DAY_OF_MONTH));
        return calendar.getInstance(TimeZone.getTimeZone("GMT+3")).get(Calendar.DAY_OF_MONTH);
    }

    private List<Map<String, Object>> compare(String answer, String word) {
        answer = new String(answer.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        word = new String(word.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);

        logger.info("comparing two strings: " + answer + " and " + word);
        logger.info("compare size of two strings: " + answer.length() + " and " + word.length());

        List<Map<String, Object>> result = new ArrayList<>();
        List<Boolean> usedLetters = new ArrayList<>(word.length());
        for (int i = 0; i < word.length(); i++) {
            usedLetters.add(false);
        }
        logger.info("usedLetters size: " + usedLetters.size());

        logger.info("starting comparing");

        for (int i = 0; i < word.length(); ++i) {
            logger.info("i is " + i + " comparing " + answer.charAt(i) + " and " + word.charAt(i));
            Map<String, Object> currentLetter = new HashMap<>();
            if (answer.charAt(i) == word.charAt(i)) {
                usedLetters.set(i, true);

                currentLetter.put("letter", word.charAt(i));
                currentLetter.put("state", "green");

                logger.info("Add letter " + currentLetter.get("letter") + " with state " + currentLetter.get("state"));

                result.add(currentLetter);

                continue;
            }

            String state = "grey";
            for (int j = 0; j < answer.length(); ++j) {
                if (usedLetters.get(j)) {
                    continue;
                }

                if (word.charAt(i) == answer.charAt(j)) {
                    usedLetters.set(j, true);
                    state = "yellow";
                    break;
                }
            }

            currentLetter.put("letter", word.charAt(i));
            currentLetter.put("state", state);

            result.add(currentLetter);
        }
        return result;
    }


    @Override
    public Map<String, Object> getAllInfo(String token) {
        Map<String, Object> result = new HashMap<>();
        User user = usersRepository.findUserByToken(token);
        String phone = user.getPhone();
        int dayOfMonth = getDayOfMonth();

        List<Attempt> attempts = attemptRepository.findAttemptsByPhoneAndDay(phone, dayOfMonth);
        Answer answer = answersRepository.getAnswerByDay(dayOfMonth);

        // check if user has already pasted word today
        boolean isEnd = false;
        if (attempts.size() == 5) {
            isEnd = true;
        }
        for (Attempt attempt : attempts) {
            if (attempt.getWord().equals(answer.getWord())) {
                isEnd = true;
                break;
            }
        }

        // if user putted feedback today, then he can't paste feedback today
        boolean isPuttedFeedback = false;
        if (user.getFeedback() == null && dayOfMonth == LAST_DAY && isEnd) {
            isPuttedFeedback = true;
        }

        List<Map<String, Object>> attemptsInfo = new ArrayList<>();

        for (Attempt attempt : attempts) {
            attemptsInfo.addAll(compare(answer.getWord(), attempt.getWord()));
        }

        result.put("letters", attemptsInfo);
        result.put("wordLength", answer.getWord().length());
        result.put("currentLine", attempts.size());
        result.put("isEnd", isEnd);
        result.put("isPuttedFeedback", isPuttedFeedback);
        result.put("post_link", answer.getPostLink());
        result.put("description", answer.getDescription());
        return result;
    }

    @Override
    public Map<String, Object> addNewWord(String token, String word) {
        String phone = usersRepository.findUserByToken(token).getPhone();
        String wortUTF = new String(word.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        logger.info("adding new word: " + wortUTF);
        if (!russianWordsRepository.isRussianWord(wortUTF)) {
            Map<String, Object> result = new HashMap<>();
            result.put("exception", "noWordInDictionary");
            return result;
        }
        int dayOfMonth = getDayOfMonth();
        Answer answer = answersRepository.getAnswerByDay(dayOfMonth);

        logger.info("answer: " + answer.getWord());
        if (attemptRepository.isExistCorrectAttempt(phone, dayOfMonth)) {
            Map<String, Object> result = new HashMap<>();
            result.put("exception", "alreadyExistCorrectAttempt");
            return result;
        }
        if (attemptRepository.findAttemptsByPhoneAndDay(phone, dayOfMonth).size() >= 5) {
            Map<String, Object> result = new HashMap<>();
            result.put("exception", "alreadyExist5Attempts");
            return result;
        }

        attemptRepository.insert(new Attempt(phone, word, attemptRepository.findAttemptsByPhoneAndDay(phone, dayOfMonth).size() + 1, dayOfMonth));

        Map<String, Object> result = new HashMap<>();
        result.put("letters", compare(answer.getWord(), word));
        result.put("isCorrect", answer.getWord().equals(word));
        return result;
    }
}
