package com.commercial.admin;

import com.commercial.admin.db.UserRepository;
import com.commercial.admin.db.entities.User;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
@AllArgsConstructor
public class Controller {
    private final Logger logger = LoggerFactory.getLogger(Controller.class);
    private final UserRepository userRepository;

    @GetMapping("/")
    public String method(Model model) {


        List<User> result = userRepository.findAll();

        model.addAttribute("userList", result);
        return "hello";
    }
}
