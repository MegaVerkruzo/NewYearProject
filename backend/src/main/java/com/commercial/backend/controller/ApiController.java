package com.commercial.backend.controller;

import com.commercial.backend.model.state.State;
import com.commercial.backend.model.state.period.AfterLotteryState;
import com.commercial.backend.model.state.period.BeforeGameState;
import com.commercial.backend.model.state.period.InGameState;
import com.commercial.backend.model.state.period.WaitFeedbackState;
import com.commercial.backend.model.state.period.WaitLotteryState;
import com.commercial.backend.model.state.period.WaitNextGameState;
import com.commercial.backend.security.response.BadRequestResponse;
import com.commercial.backend.security.response.NotRegisteredResponse;
import com.commercial.backend.service.UserService;
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
    private final UserService userService;

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
                    description = "State - wait end lottery",
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
                            schema = @Schema(implementation = NotRegisteredResponse.class)
                    )})
    })
    @GetMapping(value = "getState/v2", produces = "application/json")
    public State newGetState(@RequestHeader("authorization") String token) {
        return userService.getState(token);
    }
}
