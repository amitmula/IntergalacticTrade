package com.thoughtworks.test;

import java.util.HashMap;
import java.util.Map;

public class IntergalacticTrade {
    public enum RomanValue {
        I(1), V(5), X(10), L(50), C(100), D(500), M(1000);
        private int value;
        private RomanValue(int value) {
            this.value = value;
        }
    }
    private static final int repeatThreshold = 3;
    private static final Character[] nonRepeating = {'D', 'L', 'V'};
    private static final Character[] repeating = {'I','V','X','M'};

    private boolean checkRules(String romanNumber) {
        return checkRepetition(romanNumber) && checkNonRepetition(romanNumber);
    }

    private boolean checkNonRepetition(String romanNumber) {
        Map<Character, Integer> nonRepeating = new HashMap<Character, Integer>(){
            {
                put('D',0);
                put('L',0);
                put('V',0);
            }
        };
        for(char literal :romanNumber.toCharArray()){
            Character key = Character.toUpperCase(literal);
            if(nonRepeating.containsKey(key)) {
                Integer value = nonRepeating.get(key);
                if(value > 0) return false;
                nonRepeating.put(key, value + 1);
            }
        }
        return true;
    }

    private boolean checkRepetition(String romanNumber) {
        for(char repeatingNumeral :repeating){
            if(romanNumber.contains(new String(new char[repeatThreshold + 1]).replace("\0", String.valueOf(repeatingNumeral))))
            return false;
        }
        return true;
    }

    public int romanToDecimal(String romanNumber) throws Exception{
        if (checkRules(romanNumber)) {
            int decimal = 0;
            int lastNumber = 0;
            String romanNumeral = romanNumber.toUpperCase();
            for (int x = romanNumeral.length() - 1; x >= 0 ; x--) {
                char convertToDecimal = romanNumeral.charAt(x);

                switch (convertToDecimal) {
                    case 'M':
                        decimal = processDecimal(RomanValue.M.value, lastNumber, decimal);
                        lastNumber = 1000;
                        break;

                    case 'D':
                        decimal = processDecimal(RomanValue.D.value, lastNumber, decimal);
                        lastNumber = 500;
                        break;

                    case 'C':
                        decimal = processDecimal(RomanValue.C.value, lastNumber, decimal);
                        lastNumber = 100;
                        break;

                    case 'L':
                        decimal = processDecimal(RomanValue.L.value, lastNumber, decimal);
                        lastNumber = 50;
                        break;

                    case 'X':
                        decimal = processDecimal(RomanValue.X.value, lastNumber, decimal);
                        lastNumber = 10;
                        break;

                    case 'V':
                        decimal = processDecimal(RomanValue.V.value, lastNumber, decimal);
                        lastNumber = 5;
                        break;

                    case 'I':
                        decimal = processDecimal(RomanValue.I.value, lastNumber, decimal);
                        lastNumber = 1;
                        break;
                }
            }
            return decimal;
        } else throw new Exception("Invalid Roman Numeral");
    }

    private int processDecimal(int decimal, int lastNumber, int lastDecimal) {
        if (lastNumber > decimal) {
            return lastDecimal - decimal;
        } else {
            return lastDecimal + decimal;
        }
    }
}
