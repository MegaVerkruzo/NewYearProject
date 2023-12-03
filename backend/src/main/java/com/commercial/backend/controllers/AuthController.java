package com.commercial.backend.controllers;

import com.commercial.backend.LegacyController;
import com.commercial.backend.model.TokenException;
import com.commercial.backend.model.User;
import com.commercial.backend.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(LegacyController.class);
    private final IUserService userService;

    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public TokenException registerNewUser(@RequestBody Map<String, String> json) {
        User user = new User(json.get("phone"), json.get("name"), json.get("surname"), json.get("middleName"), json.get("email"), json.get("place"), json.get("password"), false);
        return userService.addNewUserAndGetTokenWithHistory(user);
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public TokenException loginUser(@RequestBody Map<String, String> json) {
        User user = new User();
        user.loginUser(json.get("phone"));
        logger.info("Read JSON\n" + user);
        return userService.getTokenWithCheckingPassword(user, json.get("password"));
    }
}
