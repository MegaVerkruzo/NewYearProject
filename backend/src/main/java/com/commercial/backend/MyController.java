package com.commercial.backend;

import com.commercial.backend.model.User;
import com.commercial.backend.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MyController {
    private IUserService userService;

    public MyController(IUserService cityService) {
        this.userService = cityService;
    }

    @GetMapping("/showCities")
    public String findUsers(Model model) {
        List<User> users = userService.findAll();

        model.addAttribute("users", users);

        return "showUsers";
    }
}
