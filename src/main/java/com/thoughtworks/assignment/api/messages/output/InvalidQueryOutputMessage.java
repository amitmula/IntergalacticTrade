package com.thoughtworks.assignment.api.messages.output;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class InvalidQueryOutputMessage implements OutputMessage {

    private final String message;

    public InvalidQueryOutputMessage() {
        this.message = initMessage();
    }

    private String initMessage() {
        Properties prop = new Properties();
        InputStream input = null;
        String message = "";
        try {
            input = this.getClass().getClassLoader().getResourceAsStream("messageConstants.properties");
            prop.load(input);
            message = prop.getProperty("INVALID_MESSAGE");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return message;
    }

    public String getMessage() {
        return this.message;
    }
}
