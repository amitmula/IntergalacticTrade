package com.thoughtworks.test;

import java.util.Scanner;

public class IntergalacticTradeApp {

    public static void main(String...args) throws Exception{

        IntergalacticTrade obj = new IntergalacticTrade();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("Value is " + obj.romanToDecimal(input));
    }
}
