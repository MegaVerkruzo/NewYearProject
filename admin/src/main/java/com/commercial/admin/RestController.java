package com.commercial.admin;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @GetMapping("/value")
    public Integer getValue() {
        return 5;
    }
}

