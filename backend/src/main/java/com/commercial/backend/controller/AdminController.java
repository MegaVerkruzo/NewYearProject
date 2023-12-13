package com.commercial.backend.controller;

import com.commercial.backend.db.UserRepository;
import com.commercial.backend.db.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
@AllArgsConstructor
public class AdminController {
    private final UserRepository userRepository;

    @GetMapping(value = "users", produces = "application/json")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
