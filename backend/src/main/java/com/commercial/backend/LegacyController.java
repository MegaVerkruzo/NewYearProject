package com.commercial.backend;

import com.commercial.backend.model.Answer;
import com.commercial.backend.model.TokenException;
import com.commercial.backend.model.User;
import com.commercial.backend.service.AnswersService;
import com.commercial.backend.service.IAttemptService;
import com.commercial.backend.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.Map;

import static com.commercial.backend.Common.pairToMap;

@RestController
@RequestMapping("api")
public class LegacyController {
    private final IUserService userService;
    private final IAttemptService attemptService;
    private final AnswersService answersService;

    private final Logger logger = LoggerFactory.getLogger(LegacyController.class);

    public LegacyController(IUserService userService, IAttemptService attemptService, AnswersService answersService) {
        this.userService = userService;
        this.attemptService = attemptService;
        this.answersService = answersService;
    }

    @GetMapping(value = "/check", produces = "application/json")
    public TokenException check(@RequestHeader("authorization") String token) {
        logger.info("Read HEADER\ntoken: " + token);
        return userService.checkTokenWithException(token);
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

        return attemptService.getAllInfo(user);
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

        return attemptService.addNewWord(user, answer, word, offsetDateTime);
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
        if (feedback == null || feedback.isBlank()) {
            return pairToMap("exception", "noFeedback");
        }

        if (user.getFeedback() != null && !user.getFeedback().isBlank()) {
            return pairToMap("exception", "hadFeedback");
        }

        return userService.addFeedback(user, feedback);
    }
}
