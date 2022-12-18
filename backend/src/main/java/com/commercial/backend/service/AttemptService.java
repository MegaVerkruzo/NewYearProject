package com.commercial.backend.service;

import com.commercial.backend.db.AnswersRepository;
import com.commercial.backend.db.AttemptsRepository;
import com.commercial.backend.db.RussianWordsRepository;
import com.commercial.backend.db.UsersRepository;
import com.commercial.backend.model.Answer;
import com.commercial.backend.model.User;
import com.commercial.backend.model.Attempt;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class AttemptService implements IAttemptService {

    private final int FIRST_DAY = 19;
    private final int LAST_DAY = 23;

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

    private List<Map<String, Object>> compare(String answer, String word) {
        String answerUTF = new String(answer.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        String wordUTF = new String(word.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);

        List<Map<String, Object>> result = new ArrayList<>();
        List<Boolean> usedLetters = new ArrayList<>(wordUTF.length());

        for (int i = 0; i < word.length(); ++i) {
            Map<String, Object> currentLetter = new HashMap<>();
            if (answerUTF.charAt(i) == wordUTF.charAt(i)) {
                usedLetters.set(i, true);

                currentLetter.put("letter", word.charAt(i));
                currentLetter.put("state", "green");
                result.add(currentLetter);

                continue;
            }

            String state = "grey";
            for (int j = 0; j < answer.length(); ++j) {
                if (usedLetters.get(j)) {
                    continue;
                }

                if (wordUTF.charAt(i) == answerUTF.charAt(j)) {
                    usedLetters.set(i, true);
                    state = "yellow";
                    break;
                }
            }

            currentLetter.put("letter", word.charAt(i));
            currentLetter.put("state", state);
        }
        return result;
    }

    private int getDayOfMonth() {
        return Calendar.getInstance(TimeZone.getTimeZone("GMT+3")).DAY_OF_MONTH;
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
        result.put("currentLine", attempts.size());
        result.put("isEnd", isEnd);
        result.put("isPuttedFeedback", isPuttedFeedback);
        result.put("post_link", answer.getPostLink());
        result.put("description", answer.getDescription());
        return result;
    }

    @Override
    public Map<String, Object> addNewWord(String word) {
        String wortUTF = new String(word.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        if (!russianWordsRepository.isRussianWord(wortUTF)) {
            return null;
        }
        int dayOfMonth = getDayOfMonth();
        Answer answer = answersRepository.getAnswerByDay(dayOfMonth);

        Map<String, Object> result = new HashMap<>();
        result.put("letters", compare(answer.getWord(), word));
        result.put("isCorrect", answer.getWord().equals(word));
        return result;
    }
}
