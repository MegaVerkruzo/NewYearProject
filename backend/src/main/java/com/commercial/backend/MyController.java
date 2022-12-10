package com.commercial.backend;

import com.commercial.backend.model.City;
import com.commercial.backend.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MyController {
    private ICityService cityService;

    public MyController(ICityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/showCities")
    public String findCities(Model model) {
        List<City> cities = cityService.findAll();

        model.addAttribute("cities", cities);

        return "showCities";
    }
}
