package com.commercial.backend;

import com.commercial.backend.db.AnswersRepository;
import com.commercial.backend.model.Answer;
import com.commercial.backend.model.User;
import com.commercial.backend.service.AnswersService;
import com.commercial.backend.service.IAttemptService;
import com.commercial.backend.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.*;

import static com.commercial.backend.Common.pairToMap;

@RestController
@RequestMapping("api")
public class MyController {
    private final IUserService userService;
    private final IAttemptService attemptService;
    private final AnswersService answersService;
    private final AnswersRepository answersRepository;

    private final Logger logger = LoggerFactory.getLogger(MyController.class);

    public MyController(AnswersRepository answersRepository, IUserService userService, IAttemptService attemptService, AnswersService answersService) {
        this.answersRepository = answersRepository;
        this.userService = userService;
        this.attemptService = attemptService;
        this.answersService = answersService;
    }

    @PostMapping(value = "/auth/register", consumes = "application/json", produces = "application/json")
    public Map<String, Object> registerNewUser(@RequestBody Map<String, String> json) {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User(json.get("phone"), json.get("name"), json.get("surname"), json.get("middleName"), json.get("email"), json.get("place"), json.get("password"), false);

        logger.info("Read JSON");
        Map<String, Object> result = new HashMap<>();
        Pair<String, String> tokenWithHistory = userService.addNewUserAndGetTokenWithHistory(user);
        result.put("token", tokenWithHistory.getFirst());
        result.put("exception", tokenWithHistory.getSecond());
        return result;
    }

    @PostMapping(value = "/auth/login", consumes = "application/json", produces = "application/json")
    public Map<String, Object> loginUser(@RequestBody Map<String, String> json) {
        User user = new User();
        user.loginUser(json.get("phone"));
        logger.info("Read JSON\n" + user);
        Map<String, Object> result = new HashMap<>();
        Pair<String, String> pair = userService.getTokenWithCheckingPassword(user, json.get("password"));
        result.put("token", pair.getFirst());
        result.put("exception", pair.getSecond());
        return result;
    }

    @GetMapping(value = "/check", produces = "application/json")
    public Map<String, Object> check(@RequestHeader("authorization") String token) {
        logger.info("Read HEADER\ntoken: " + token);
        Map<String, Object> result = new HashMap<>();
        Pair<String, String> pair = userService.checkTokenWithException(token);
        result.put("token", pair.getFirst());
        result.put("exception", pair.getSecond());
        return result;
    }

    // :APPROVED
    @GetMapping(value = "/game", produces = "application/json")
    public Map<String, Object> trying(@RequestHeader("authorization") String token) {
        if (token == null) {
            return pairToMap("exception", "noUser");
        }

        User user = userService.getUserByToken(token);
        logger.info("Read user " + user);

        if (user == null) {
            return pairToMap("exception", "noUser");
        }

        Map<String, Object> result = attemptService.getAllInfo(user);
        return result;
    }


    // :APPROVED
    @PostMapping(value = "/game/new_attempt", consumes = "application/json", produces = "application/json")
    public Map<String, Object> newAttempt(@RequestHeader("authorization") String token, @RequestBody Map<String, String> json) {
        if (token == null) {
            return pairToMap("exception", "noUser");
        }

        User user = userService.getUserByToken(token);
        logger.info("Read user " + user);

        if (user == null) {
            return pairToMap("exception", "noUser");
        }
        if (json == null) {
            return pairToMap("exception", "uncorrectedData");
        }

        String word = json.get("word");
        logger.info("Read word " + word);
        if (word == null) {
            return pairToMap("exception", "uncorrectedData");
        }

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        Answer answer = answersService.findPreviousAnswer(offsetDateTime);
        logger.info("answer: " + answer);

        if (answer == null) {
            return pairToMap("exception", "uncorrectedData");
        }

        if (answer.getWord().length() != word.length()) {
            return pairToMap("exception", "wrongSize");
        }

        Map<String, Object> result = attemptService.addNewWord(user, answer, word, offsetDateTime);
        return result;
    }

    // :APPROVED
    @PostMapping(value = "/feedback", consumes = "application/json", produces = "application/json")
    public Map<String, Object> addFeedback(@RequestHeader("authorization") String token, @RequestBody Map<String, String> json) {
        if (token == null) {
            return pairToMap("exception", "noUser");
        }

        User user = userService.getUserByToken(token);
        logger.info("Read user " + user);
        if (user == null) {
            return pairToMap("exception", "noUser");
        }

        String feedback = json.get("feedback");
        logger.info("Read feedback " + feedback);
        if (feedback == null || feedback.length() == 0) {
            return pairToMap("exception", "noFeedback");
        }

        if (user.getFeedback() != null && user.getFeedback().length() > 0) {
            return pairToMap("exception", "hadFeedback");
        }

        return userService.addFeedback(user, feedback);
    }
}
