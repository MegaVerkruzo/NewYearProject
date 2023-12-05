package com.commercial.backend.controllers;

import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.auth.InputRegistration;
import com.commercial.backend.model.auth.TokenException;
import com.commercial.backend.service.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final IUserService userService;

    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "register/v2", consumes = "application/json", produces = "application/json")
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
}
