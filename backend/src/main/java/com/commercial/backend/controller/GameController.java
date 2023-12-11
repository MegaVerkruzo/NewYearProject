package com.commercial.backend.controller;

import com.commercial.backend.db.entities.Answer;
import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.game.GameStateKlass;
import com.commercial.backend.model.json.JsonWord;
import com.commercial.backend.model.state.period.InGameState;
import com.commercial.backend.model.state.period.WaitFeedbackState;
import com.commercial.backend.security.ApiException;
import com.commercial.backend.security.response.AlreadyExist5AttemptsResponse;
import com.commercial.backend.security.response.AlreadyExistCorrectAttemptResponse;
import com.commercial.backend.security.response.NoWordInDictionaryResponse;
import com.commercial.backend.security.response.NotFoundUserResponse;
import com.commercial.backend.security.response.NotValidResponse;
import com.commercial.backend.service.interfaces.IAnswersService;
import com.commercial.backend.service.interfaces.IAttemptService;
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

import java.time.OffsetDateTime;

@RestController
@RequestMapping("api/game")
@AllArgsConstructor
public class GameController {
    private final Logger logger = LoggerFactory.getLogger(GameController.class);
    private final IUserService userService;
    private final IAttemptService attemptService;
    private final IAnswersService answersService;

    //    @GetMapping(value = "v2", produces = "application/json")
//    public GameStateKlass trying(@RequestHeader("authorization") String token) {
//        // :TODO delete copypaste
//        if (token == null) {
//            return GameStateKlass.createStateWithException(ApiException.noUser);
//        }
//
//        User user = userService.getUserByToken(token);
//        logger.info("Read user " + user);
//
//        if (user == null) {
//            return GameStateKlass.createStateWithException(ApiException.noUser);
//        }
//
//        return attemptService.getAllInfo(user);
//    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "State - in game",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = InGameState.class)
                    )}),
            @ApiResponse(
                    responseCode = "202",
                    description = "State - wait feedback",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = WaitFeedbackState.class)
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
                    )}),
            @ApiResponse(
                    responseCode = "402",
                    description = "No word in dictionary",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NoWordInDictionaryResponse.class)
                    )}),
            @ApiResponse(
                    responseCode = "403",
                    description = "Already exist correct attempt",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlreadyExistCorrectAttemptResponse.class)
                    )}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Already exist 5 attempts",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlreadyExist5AttemptsResponse.class)
                    )}),
    })
    @PostMapping(value = "new_attempt/v2", consumes = "application/json", produces = "application/json")
    // :TODO find good thing for deleting unnecessary objects
    public GameStateKlass newAttempt(@RequestHeader("authorization") String token, @RequestBody JsonWord jsonWordBody) {
        if (token == null) {
            return GameStateKlass.createStateWithException(ApiException.notRegistered);
        }

        User user = userService.getUserByToken(token);
        logger.info("Read user " + user);

        if (user == null) {
            return GameStateKlass.createStateWithException(ApiException.notRegistered);
        }
        if (jsonWordBody == null) {
            return GameStateKlass.createStateWithException(ApiException.uncorrectedData);
        }

        String word = jsonWordBody.getWord();
        logger.info("Read word " + word);
        if (word == null) {
            return GameStateKlass.createStateWithException(ApiException.uncorrectedData);
        }

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        Answer answer = answersService.findPreviousAnswer(offsetDateTime);
        logger.info("answer: " + answer);

        if (answer == null) {
            return GameStateKlass.createStateWithException(ApiException.uncorrectedData);
        }

        if (answer.getWord().length() != word.length()) {
            return GameStateKlass.createStateWithException(ApiException.wrongSize);
        }

        return attemptService.addNewWord(user, answer, word, offsetDateTime);
    }
}
