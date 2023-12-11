package com.commercial.backend.controller;

import com.commercial.backend.exception.bad.request.BadRequestResponse;
import com.commercial.backend.exception.no.user.NotFoundUserResponse;
import com.commercial.backend.model.game.GameState;
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
                    description = "Found the user",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GameState.class)
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
    public GameState newGetState(@RequestHeader("authorization") String authorization) {
        return userService.checkTokenWithException(authorization);
    }
}
