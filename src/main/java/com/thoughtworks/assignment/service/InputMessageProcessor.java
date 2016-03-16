package com.thoughtworks.assignment.service;

public class InputMessageProcessor {

    private static String Error = "I have no idea what you are talking about";
    private static String rgxAssignment = "^([a-z]+) is ([I|V|X|L|C|D|M])$";
    private static String rgxCredits = "((?:[a-z]+ )+)([A-Z]\\w+) is (\\d+) ([A-Z]\\w+)$";
    private static String rgxHowMany= "^how many ([a-zA-Z]\\w+) is ((?:\\w+ )+)([A-Z]\\w+) \\?$";
    private static String rgxHowMuch = "^how much is ((?:\\w+[^0-9] )+)\\?$";

    public void process(String message) {

    }
}
