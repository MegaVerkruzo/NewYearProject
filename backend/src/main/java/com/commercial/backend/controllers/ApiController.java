package com.commercial.backend.controllers;

import com.commercial.backend.model.game.GameState;
import com.commercial.backend.service.IUserService;
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

    @GetMapping(value = "getState/v2", produces = "application/json")
    public GameState newGetState(@RequestHeader("authorization") String authorization) {
        return userService.checkTokenWithException(authorization);
    }
}
