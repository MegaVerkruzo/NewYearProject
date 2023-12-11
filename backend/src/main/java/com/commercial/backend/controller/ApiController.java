package com.commercial.backend.controller;

import com.commercial.backend.exception.bad.request.BadRequestResponse;
import com.commercial.backend.exception.no.user.NotFoundUserResponse;
import com.commercial.backend.model.game.GameStateKlass;
import com.commercial.backend.model.state.AfterLotteryState;
import com.commercial.backend.model.state.BeforeGameState;
import com.commercial.backend.model.state.InGameState;
import com.commercial.backend.model.state.WaitFeedbackState;
import com.commercial.backend.model.state.WaitLotteryState;
import com.commercial.backend.model.state.WaitNextGameState;
import com.commercial.backend.service.interfaces.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class ApiController {
    private final IUserService userService;

    @Operation(summary = "Getting state")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "State - before game",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BeforeGameState.class)
                    )}),
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
                    responseCode = "205",
                    description = "State - after lottery",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AfterLotteryState.class)
                    )}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BadRequestResponse.class)
                    )}),
            @ApiResponse(
                    responseCode = "401",
                    description = "Not authorized",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NotFoundUserResponse.class)
                    )})
    })
    @GetMapping(value = "getState/v2", produces = "application/json")
    public GameStateKlass newGetState(@RequestHeader("authorization") String authorization) {
        return userService.checkTokenWithException(authorization);
    }
}
