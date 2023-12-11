package com.commercial.backend.controller;

import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.json.JsonFeedback;
import com.commercial.backend.model.state.State;
import com.commercial.backend.model.state.period.InGameState;
import com.commercial.backend.model.state.period.WaitFeedbackState;
import com.commercial.backend.model.state.period.WaitLotteryState;
import com.commercial.backend.model.state.period.WaitNextGameState;
import com.commercial.backend.security.exception.NotRegisteredException;
import com.commercial.backend.security.exception.NotValidException;
import com.commercial.backend.security.response.NotRegisteredResponse;
import com.commercial.backend.security.response.NotValidResponse;
import com.commercial.backend.service.interfaces.IUserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/feedback")
@AllArgsConstructor
public class FeedbackController {
    private final Logger logger = LoggerFactory.getLogger(FeedbackController.class);
    private final IUserService userService;

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "State - in game",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = InGameState.class)
                    )}),
            @ApiResponse(
                    responseCode = "203",
                    description = "State - wait next game",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = WaitNextGameState.class)
                    )}),
            @ApiResponse(
                    responseCode = "204",
                    description = "State - wait lottery",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = WaitLotteryState.class)
                    )}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Not valid",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NotValidResponse.class)
                    )}),
            @ApiResponse(
                    responseCode = "401",
                    description = "Not authorized",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NotRegisteredResponse.class)
                    )})
    })
    @PostMapping(value = "v2", consumes = "application/json", produces = "application/json")
    public State addFeedback(
            @RequestHeader("authorization") String token,
            @RequestBody JsonFeedback jsonFeedback
    ) {
        if (token == null) {
            throw new NotRegisteredException();
        }

        User user = userService.getUserByToken(token);
        logger.info("Read user " + user);
        if (user == null) {
            throw new NotRegisteredException();
        }

        String feedback = jsonFeedback.getFeedback();
        logger.info("Read feedback " + feedback);
        if (feedback == null || feedback.isBlank()) {
            throw new NotValidException();
        }

        // :TODO change logic of feedback
//        if (user.getFeedback() != null && !user.getFeedback().isBlank()) {
//            return new Feedback(hadFeedback);
//        }

        // :TODO change it
//        return userService.addFeedback(user, feedback);
        return new WaitFeedbackState(null, null, null, null);
    }
}
