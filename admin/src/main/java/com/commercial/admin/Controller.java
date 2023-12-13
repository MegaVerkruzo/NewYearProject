package com.commercial.admin;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
@AllArgsConstructor
public class Controller {
    private final Logger logger = LoggerFactory.getLogger(Controller.class);
    private final WebClient webClient;

    @GetMapping("/")
    public String method(Model model) {
        Flux<User> users = webClient.get()
                .uri("/admin/users")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToFlux(User.class);

        List<User> result = new ArrayList<>();
        users.subscribe(result::add);

        model.addAttribute("userList", result);
        return "hello";
    }
}
