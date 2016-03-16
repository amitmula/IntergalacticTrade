package com.thoughtworks.assignment.api;

public enum RomanValue {
    I(1), V(5), X(10), L(50), C(100), D(500), M(1000);
    private int value;
    private RomanValue(int val){
        this.value = val;
    }
    public int getValue() { return this.value; }
}