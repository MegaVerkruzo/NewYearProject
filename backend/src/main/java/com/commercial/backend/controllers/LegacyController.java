package com.commercial.backend.controllers;

import com.commercial.backend.db.entities.Answer;
import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.ApiException;
import com.commercial.backend.model.auth.InputLogin;
import com.commercial.backend.model.auth.InputRegistration;
import com.commercial.backend.model.auth.TokenException;
import com.commercial.backend.model.feedback.Feedback;
import com.commercial.backend.model.feedback.FeedbackInput;
import com.commercial.backend.model.game.GameState;
import com.commercial.backend.model.game.JsonWord;
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

import static com.commercial.backend.model.ApiException.hadFeedback;
import static com.commercial.backend.model.ApiException.noFeedback;
import static com.commercial.backend.model.ApiException.noUser;

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

    @PostMapping(value = "/feedback", consumes = "application/json", produces = "application/json")
    public Feedback addFeedback(
            @RequestHeader("authorization") String token,
            @RequestBody FeedbackInput feedbackInput
    ) {
        if (token == null) {
            return new Feedback(noUser);
        }

        User user = userService.getUserByToken(token);
        logger.info("Read user " + user);
        if (user == null) {
            return new Feedback(noUser);
        }

        String feedback = feedbackInput.feedback;
        logger.info("Read feedback " + feedback);
        if (feedback == null || feedback.isBlank()) {
            return new Feedback(noFeedback);
        }

        if (user.getFeedback() != null && !user.getFeedback().isBlank()) {
            return new Feedback(hadFeedback);
        }

        return userService.addFeedback(user, feedback);
    }

    @PostMapping(value = "auth/register", consumes = "application/json", produces = "application/json")
    public TokenException registerNewUser(@RequestBody InputRegistration registration) {
        return userService.addNewUserAndGetTokenWithHistory(
                new User(
                        registration.phone,
                        registration.name,
                        registration.surname,
                        registration.middleName,
                        registration.email,
                        registration.place,
                        registration.password,
                        false
                ));
    }

    @PostMapping(value = "auth/login", consumes = "application/json", produces = "application/json")
    public TokenException loginUser(@RequestBody InputLogin login) {
        User user = new User();
        user.loginUser(login.phone);
        logger.info("Read JSON\n" + user);
        return userService.getTokenWithCheckingPassword(user, login.password);
    }

    @GetMapping(value = "game", produces = "application/json")
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

    @PostMapping(value = "game/new_attempt", consumes = "application/json", produces = "application/json")
    // :TODO find good thing for deleting unnecessary objects
    public GameState newAttempt(@RequestHeader("authorization") String token, @RequestBody JsonWord wordBody) {
        if (token == null) {
            return GameState.createStateWithException(ApiException.noUser);
        }

        User user = userService.getUserByToken(token);
        logger.info("Read user " + user);

        if (user == null) {
            return GameState.createStateWithException(ApiException.noUser);
        }
        if (wordBody == null) {
            return GameState.createStateWithException(ApiException.uncorrectedData);
        }

        String word = wordBody.word;
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
