package com.commercial.backend.model.state;

import lombok.Getter;

@Getter
public class State {
    private final String text;

    public State(String text) {
        this.text = text;
    }
}
