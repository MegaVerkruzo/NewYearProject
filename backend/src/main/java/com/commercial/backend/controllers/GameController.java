package com.commercial.backend.controllers;

import com.commercial.backend.LegacyController;
import com.commercial.backend.db.entities.Answer;
import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.ApiException;
import com.commercial.backend.model.game.GameState;
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
        // :TODO delete copypaste
        if (token == null) {
            return GameState.createStateWithException(ApiException.noUser);
        }

        User user = userService.getUserByToken(token);
        logger.info("Read user " + user);

        if (user == null) {
            return GameState.createStateWithException(ApiException.noUser);
        }

        return attemptService.getAllInfo(user);
    }

    @PostMapping(value = "/new_attempt", consumes = "application/json", produces = "application/json")
    public GameState newAttempt(@RequestHeader("authorization") String token, @RequestBody Map<String, String> json) {
        if (token == null) {
            return GameState.createStateWithException(ApiException.noUser);
        }

        User user = userService.getUserByToken(token);
        logger.info("Read user " + user);

        if (user == null) {
            return GameState.createStateWithException(ApiException.noUser);
        }
        if (json == null) {
            return GameState.createStateWithException(ApiException.uncorrectedData);
        }

        String word = json.get("word");
        logger.info("Read word " + word);
        if (word == null) {
            return GameState.createStateWithException(ApiException.uncorrectedData);
        }

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        Answer answer = answersService.findPreviousAnswer(offsetDateTime);
        logger.info("answer: " + answer);

        if (answer == null) {
            return GameState.createStateWithException(ApiException.uncorrectedData);
        }

        if (answer.getWord().length() != word.length()) {
            return GameState.createStateWithException(ApiException.wrongSize);
        }

        return attemptService.addNewWord(user, answer, word, offsetDateTime);
    }
}
