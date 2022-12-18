package com.commercial.backend;

import com.commercial.backend.model.User;
import com.commercial.backend.service.IAttemptService;
import com.commercial.backend.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class MyController {

    private final IUserService userService;
    private final IAttemptService attemptService;

    private final Logger logger = LoggerFactory.getLogger(MyController.class);

    public MyController(IUserService userService, IAttemptService attemptService) {
        this.userService = userService;
        this.attemptService = attemptService;
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

    private Map<String, Object> letterToMap(String letter, String state) {
        Map<String, Object> map = new HashMap<>();
        map.put("letter", letter);
        map.put("state", state);
        return map;
    }

    @GetMapping(value = "/game", consumes = "application/json",produces = "application/json")
    public Map<String, Object> trying(@RequestHeader("authorization") String token) {
        if (check(token).get("exception") != "") {
            return check(token);
        }

        Map<String, Object> result = attemptService.getAllInfo(token);

        result.put("exception", "");
        return result;
    }

    @PostMapping(value = "/game/new_attempt", consumes = "application/json", produces = "application/json")
    public Map<String, Object> newAttempt(@RequestHeader("authorization") String token, @RequestBody Map<String, String> json) {
        logger.info("Read JSON\nword: " + json.get("word"));
        if (check(token).get("exception") != "") {
            return check(token);
        }

        Map<String, Object> result = attemptService.addNewWord(token, json.get("word"));

        if (result == null) {
            result = new HashMap<>();
            result.put("exception", "noWordInDictionary");
            return result;
        }

        result.put("exception", "");
        return result;
    }
}
