package com.commercial.backend.model.state.period;

import com.commercial.backend.model.state.State;
import lombok.Getter;

@Getter
public class AttemptState extends State {
    private final Boolean shouldSound;

    public AttemptState(Boolean shouldSound) {
        this.shouldSound = shouldSound;
    }
}
