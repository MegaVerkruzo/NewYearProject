package com.commercial.backend.controllers;

import com.commercial.backend.model.game.GameState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.commercial.backend.model.game.GameState.createEmptyState;

@RestController
@RequestMapping("api")
public class ApiController {
    @GetMapping(value = "getState/v2", produces = "application/json")
    public GameState newGetState(@RequestHeader("authorization") String authorization) {
        return createEmptyState();
    }
}
