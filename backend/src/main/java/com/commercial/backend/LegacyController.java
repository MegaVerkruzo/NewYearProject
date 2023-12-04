package com.commercial.backend;

import com.commercial.backend.model.Feedback;
import com.commercial.backend.model.FeedbackInput;
import com.commercial.backend.model.TokenException;
import com.commercial.backend.model.User;
import com.commercial.backend.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.commercial.backend.model.ApiException.hadFeedback;
import static com.commercial.backend.model.ApiException.noFeedback;
import static com.commercial.backend.model.ApiException.noUser;

@RestController
@RequestMapping("api")
public class LegacyController {
    private final IUserService userService;

    private final Logger logger = LoggerFactory.getLogger(LegacyController.class);

    public LegacyController(IUserService userService) {
        this.userService = userService;
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
}
