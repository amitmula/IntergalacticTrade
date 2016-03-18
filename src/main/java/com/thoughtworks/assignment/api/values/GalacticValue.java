package com.thoughtworks.assignment.api.values;

public class GalacticValue {
    String name;
    int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public GalacticValue(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
