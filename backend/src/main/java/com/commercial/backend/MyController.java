package com.commercial.backend;

import com.commercial.backend.model.User;
import com.commercial.backend.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class MyController {

    private final IUserService userService;
    private final Logger logger = LoggerFactory.getLogger(MyController.class);

    public MyController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register", consumes = "application/json")
    public Map<String, Object> registerNewUser(@RequestBody Map<String, String> json) {
        User user = new User(json.get("phone"), json.get("name"), json.get("surname"), json.get("middleName"), json.get("email"), json.get("place"), json.get("password"), false);
        logger.info("Read JSON");
        HashMap<String, Object> result = new HashMap<>();
        Pair<String, Boolean> tokenWithHistory = userService.addNewUserAndGetTokenWithHistory(user);
        result.put("token", tokenWithHistory.getFirst());
        result.put("isRegisteredBefore", tokenWithHistory.getSecond());
        return result;
    }

//    @PostMapping(value = "/login", consumes = "application/json")
//    public Map<String, Object> loginUser(@RequestBody Map<String, String> json) {
//
//    }
}
