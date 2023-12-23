package com.commercial.admin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@AllArgsConstructor
@ToString
public class FeedbackTable {
    private final String phone;
    private final String question;
    private final String answer;
}
