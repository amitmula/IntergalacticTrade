package com.thoughtworks.assignment.trader;

import com.thoughtworks.assignment.service.InputMessageProcessor;
import com.thoughtworks.assignment.service.OutputMessageProcessor;

import java.util.ArrayList;
import java.util.List;

public class IntergalacticTradeManager {

    private OutputMessageProcessor outputProcessor;

    public List<String> process(List<String> inputMessages) {

        InputMessageProcessor inputProcessor = new InputMessageProcessor();
        List<String> outputMessages = new ArrayList<String>();

        for(String message: inputMessages) {
            outputMessages.add(inputProcessor.process(message).getMessage());
        }
        return outputMessages;
    }
}
