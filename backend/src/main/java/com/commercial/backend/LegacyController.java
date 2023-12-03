package com.commercial.backend;

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

import java.util.Map;

import static com.commercial.backend.Common.pairToMap;

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
