package com.commercial.backend;

import com.commercial.backend.model.User;
import com.commercial.backend.service.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class MyController {

    private ObjectMapper objectMapper;
    private IUserService userService;
    private Logger logger = LoggerFactory.getLogger(MyController.class);

    public MyController(IUserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    public Map<String, Object> registerNewUser(@RequestBody Map<String, String> json) {
        User user = new User(json.get("phone"), json.get("name"), json.get("surname"), json.get("middleName"), json.get("email"), json.get("place"), json.get("password"));
        logger.info("Read JSON");
        HashMap<String, Object> result = new HashMap<>();
        Pair<String, Boolean> tokenWithHistory = userService.addNewUserAndGetTokenWithHistory(user);
        result.put("token", tokenWithHistory.getFirst());
        result.put("isRegisteredBefore", tokenWithHistory.getSecond());
        return result;
    }
}
