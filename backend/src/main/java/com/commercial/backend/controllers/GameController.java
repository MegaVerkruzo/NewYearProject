package com.commercial.backend.controllers;

import com.commercial.backend.LegacyController;
import com.commercial.backend.model.Answer;
import com.commercial.backend.model.GameState;
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
@RequestMapping("api/game")
public class GameController {
    private final Logger logger = LoggerFactory.getLogger(LegacyController.class);
    private final IUserService userService;
    private final IAttemptService attemptService;
    private final AnswersService answersService;

    public GameController(IUserService userService, IAttemptService attemptService, AnswersService answersService) {
        this.userService = userService;
        this.attemptService = attemptService;
        this.answersService = answersService;
    }

    @GetMapping(produces = "application/json")
    public GameState trying(@RequestHeader("authorization") String token) {
        if (token == null) {
            return GameState.createNoUserGameState();
        }

        User user = userService.getUserByToken(token);
        logger.info("Read user " + user);

        if (user == null) {
            return GameState.createNoUserGameState();
        }

        return attemptService.getAllInfo(user);
    }

    @PostMapping(value = "/new_attempt", consumes = "application/json", produces = "application/json")
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
}
