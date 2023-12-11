package com.commercial.backend.controllers;

import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.feedback.FeedbackInput;
import com.commercial.backend.model.game.GameState;
import com.commercial.backend.service.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.commercial.backend.model.ApiException.noFeedback;
import static com.commercial.backend.model.ApiException.noUser;
import static com.commercial.backend.model.game.GameState.createEmptyState;
import static com.commercial.backend.model.game.GameState.createStateWithException;

@RestController
@RequestMapping("api/feedback")
@AllArgsConstructor
public class FeedbackController {
    private final Logger logger = LoggerFactory.getLogger(FeedbackController.class);
    private final IUserService userService;

    @PostMapping(value = "v2", consumes = "application/json", produces = "application/json")
    public GameState addFeedback(
            @RequestHeader("authorization") String token,
            @RequestBody FeedbackInput feedbackInput
    ) {
        if (token == null) {
            return createStateWithException(noUser);
        }

        User user = userService.getUserByToken(token);
        logger.info("Read user " + user);
        if (user == null) {
            return createStateWithException(noUser);
        }

        String feedback = feedbackInput.feedback;
        logger.info("Read feedback " + feedback);
        if (feedback == null || feedback.isBlank()) {
            return createStateWithException(noFeedback);
        }

        // :TODO change logic of feedback
//        if (user.getFeedback() != null && !user.getFeedback().isBlank()) {
//            return new Feedback(hadFeedback);
//        }

        // :TODO change it
//        return userService.addFeedback(user, feedback);
        return createEmptyState();
    }
}
