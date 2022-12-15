package com.commercial.backend;

import com.commercial.backend.model.User;
import com.commercial.backend.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
public class MyController {

    private final IUserService userService;
    private final Logger logger = LoggerFactory.getLogger(MyController.class);

    public MyController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/auth/register", consumes = "application/json", produces = "application/json")
    public Map<String, Object> registerNewUser(@RequestBody Map<String, String> json) {
        User user = new User(json.get("phone"), json.get("name"), json.get("surname"), json.get("middleName"), json.get("email"), json.get("place"), json.get("password"), false);
        logger.info("Read JSON");
        Map<String, Object> result = new HashMap<>();
        Pair<String, String> tokenWithHistory = userService.addNewUserAndGetTokenWithHistory(user);
        result.put("token", tokenWithHistory.getFirst());
        result.put("exception", tokenWithHistory.getSecond());
        return result;
    }

    @PostMapping(value = "/auth/login", consumes = "application/json", produces = "application/json")
    public Map<String, Object> loginUser(@RequestBody Map<String, String> json) {
        User user = new User();
        user.loginUser(json.get("phone"));
        logger.info("Read JSON\n" + user);
        Map<String, Object> result = new HashMap<>();
        Pair<String, String> pair = userService.getTokenWithCheckingPassword(user, json.get("password"));
        result.put("token", pair.getFirst());
        result.put("exception", pair.getSecond());
        return result;
    }

    @GetMapping(value = "/check", produces = "application/json")
    public Map<String, Object> check(@RequestHeader("authorization") String token) {
        logger.info("Read HEADER\ntoken: " + token);
        Map<String, Object> result = new HashMap<>();
        Pair<String, String> pair = userService.checkTokenWithException(token);
        result.put("token", pair.getFirst());
        result.put("exception", pair.getSecond());
        return result;
    }
}
