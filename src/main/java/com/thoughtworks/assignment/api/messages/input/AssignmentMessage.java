package com.thoughtworks.assignment.api.messages.input;

import com.thoughtworks.assignment.api.values.GalacticValue;
import com.thoughtworks.assignment.api.values.RomanValue;

public class AssignmentMessage implements InputMessage {

    final String type = "Assignment";
    GalacticValue gv;
    RomanValue rv;

    public AssignmentMessage(GalacticValue gv, RomanValue rv) {
        this.gv =  gv;
        this.rv = rv;
    }

    public String getType() {
        return type;
    }
}
