package com.commercial.backend.controllers;

import com.commercial.backend.LegacyController;
import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.auth.InputLogin;
import com.commercial.backend.model.auth.InputRegistration;
import com.commercial.backend.model.auth.TokenException;
import com.commercial.backend.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(LegacyController.class);
    private final IUserService userService;

    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public TokenException registerNewUser(@RequestBody InputRegistration registration) {
        return userService.addNewUserAndGetTokenWithHistory(
                new User(
                        registration.phone,
                        registration.name,
                        registration.surname,
                        registration.middleName,
                        registration.email,
                        registration.place,
                        registration.password,
                        false
                ));
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public TokenException loginUser(@RequestBody InputLogin login) {
        User user = new User();
        user.loginUser(login.phone);
        logger.info("Read JSON\n" + user);
        return userService.getTokenWithCheckingPassword(user, login.password);
    }
}
