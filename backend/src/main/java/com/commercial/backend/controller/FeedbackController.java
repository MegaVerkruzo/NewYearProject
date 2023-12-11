package com.commercial.backend.controller;

import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.game.GameStateKlass;
import com.commercial.backend.model.json.JsonFeedback;
import com.commercial.backend.model.state.period.InGameState;
import com.commercial.backend.model.state.period.WaitLotteryState;
import com.commercial.backend.model.state.period.WaitNextGameState;
import com.commercial.backend.security.response.NotFoundUserResponse;
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

import static com.commercial.backend.model.game.GameStateKlass.createEmptyState;
import static com.commercial.backend.model.game.GameStateKlass.createStateWithException;
import static com.commercial.backend.security.ApiException.noFeedback;
import static com.commercial.backend.security.ApiException.notRegistered;

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
                            schema = @Schema(implementation = NotFoundUserResponse.class)
                    )})
    })
    @PostMapping(value = "v2", consumes = "application/json", produces = "application/json")
    public GameStateKlass addFeedback(
            @RequestHeader("authorization") String token,
            @RequestBody JsonFeedback jsonFeedback
    ) {
        if (token == null) {
            return createStateWithException(notRegistered);
        }

        User user = userService.getUserByToken(token);
        logger.info("Read user " + user);
        if (user == null) {
            return createStateWithException(notRegistered);
        }

        String feedback = jsonFeedback.getFeedback();
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
