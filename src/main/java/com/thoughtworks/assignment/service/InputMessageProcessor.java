package com.thoughtworks.assignment.service;

import com.thoughtworks.assignment.api.messages.input.InputMessage;
import com.thoughtworks.assignment.api.messages.output.InvalidQueryOutputMessage;
import com.thoughtworks.assignment.api.messages.output.OutputMessage;
import com.thoughtworks.assignment.api.values.EarthMetal;
import com.thoughtworks.assignment.api.values.GalacticValue;
import com.thoughtworks.assignment.api.values.RomanValue;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputMessageProcessor {
    private static String rgxAssignment = "^([a-z]+) is ([I|V|X|L|C|D|M])$";
    private static String rgxCredits = "((?:[a-z]+ )+)([A-Z]\\w+) is (\\d+) ([A-Z]\\w+)$";
    private static String rgxHowMany= "^how many ([a-zA-Z]\\w+) is ((?:\\w+ )+)([A-Z]\\w+) \\?$";
    private static String rgxHowMuch = "^how much is ((?:\\w+[^0-9] )+)\\?$";

    Map<GalacticValue, RomanValue> galacticValues;
    Map<EarthMetal, Integer> earthMetalValues;

    public OutputMessage process(String inputMessage) {

        OutputMessage outputMessage = null;

        switch(checkMessageType(inputMessage)){
            case "Assignment" :
                Pattern ptn = Pattern.compile(rgxAssignment);
                Matcher mcher = ptn.matcher(inputMessage);
                mcher.matches();
                String name = mcher.group(1).trim();
                String roman = mcher.group(2).trim();
            break;

            default:
                outputMessage = new InvalidQueryOutputMessage();
        }

        return outputMessage;
    }

    private String checkMessageType(String s){
        String flag = null;
        String[] rgxArray = new String[]{rgxAssignment,rgxCredits,rgxHowMany,rgxHowMuch};
        for(int i = 0;i<rgxArray.length;i++){
            Pattern ptn = Pattern.compile(rgxArray[i]);
            Matcher mcher = ptn.matcher(s);
            if(mcher.matches()){
                switch(i){
                    case 0:
                        flag = "assignment";
                        break;
                    case 1:
                        flag = "credits";
                        break;
                    case 2:
                        flag = "howmany";
                        break;
                    case 3:
                        flag = "howmuch";
                        break;
                    default:
                        break;
                }
            }
        }
        return flag;
    }
}
