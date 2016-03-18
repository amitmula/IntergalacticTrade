package com.thoughtworks.assignment.api.values;

public class EarthMetal {
    String name;
    int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public EarthMetal(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
