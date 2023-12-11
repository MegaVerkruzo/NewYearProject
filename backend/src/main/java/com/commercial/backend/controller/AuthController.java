package com.commercial.backend.controller;

import com.commercial.backend.model.json.JsonRegistration;
import com.commercial.backend.model.state.State;
import com.commercial.backend.model.state.period.AfterLotteryState;
import com.commercial.backend.model.state.period.BeforeGameState;
import com.commercial.backend.model.state.period.InGameState;
import com.commercial.backend.model.state.period.WaitLotteryState;
import com.commercial.backend.model.state.period.WaitNextGameState;
import com.commercial.backend.security.response.NotValidResponse;
import com.commercial.backend.security.response.UserExistsResponse;
import com.commercial.backend.service.interfaces.IUserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {
    private final IUserService userService;

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
                    description = "Not valid",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NotValidResponse.class)
                    )}),
            @ApiResponse(
                    responseCode = "401",
                    description = "Already registered",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserExistsResponse.class)
                    )})
    })
    @PostMapping(value = "register/v2", consumes = "application/json", produces = "application/json")
    public State registerNewUser(@RequestBody JsonRegistration registration) {
        return userService.addNewUserAndGetTokenWithHistory(registration.getUser());
    }
}
