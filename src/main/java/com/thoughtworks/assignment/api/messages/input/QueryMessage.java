package com.thoughtworks.assignment.api.messages.input;

import com.thoughtworks.assignment.api.messages.input.InputMessage;

public class QueryMessage implements InputMessage {

    String type;

    public QueryMessage(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
