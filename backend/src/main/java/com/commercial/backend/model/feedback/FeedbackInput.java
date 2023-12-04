package com.commercial.backend.model.feedback;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedbackInput {
    @JsonProperty("feedback")
// :TODO remove public
    public String feedback;
}
