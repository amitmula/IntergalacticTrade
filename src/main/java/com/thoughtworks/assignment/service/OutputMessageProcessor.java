package com.thoughtworks.assignment.service;

import com.thoughtworks.assignment.api.messages.output.OutputMessage;

public class OutputMessageProcessor {

    OutputMessage outputMessage;
    public OutputMessageProcessor(OutputMessage message) {
        this.outputMessage = message;
    }

    public String getOutputMessage() {
        return outputMessage.getMessage();
    }

}
