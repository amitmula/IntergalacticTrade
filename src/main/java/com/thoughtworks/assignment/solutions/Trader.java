package com.thoughtworks.assignment.solutions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author wangshusen(shusonwang@gmail.com)
 */
public class Trader {
    private static String Error = "I have no idea what you are talking about";
    private static String rgxAssignment = "^([a-z]+) is ([I|V|X|L|C|D|M])$";
    private static String rgxCredits = "((?:[a-z]+ )+)([A-Z]\\w+) is (\\d+) ([A-Z]\\w+)$";
    private static String rgxHowMany= "^how many ([a-zA-Z]\\w+) is ((?:\\w+ )+)([A-Z]\\w+) \\?$";
    private static String rgxHowMuch = "^how much is ((?:\\w+[^0-9] )+)\\?$";
    /**
     * @param args the command line arguments
     */

    private static HashMap<String, String> transList = new HashMap<String, String>();
    private static HashMap<String, Double> curryList = new HashMap<String, Double>();

    public static void main(String[] args) {
        BufferedReader br = null;
        String currency = "";

        try {
            br = new BufferedReader(new FileReader("input.txt"));
            String line = br.readLine();
            while(line!=null){
                String flag = checkFlag(line);

                /***
                 * idea design
                 * step 1: get the flag and proceed it accordingly
                 * step 2: construct the symbol list with roman
                 * step 3: construct currency and credits
                 * step 4: process the questions
                 * step 5: handle error
                 */

                if(flag.equals("assignment")){
                    Pattern ptn = Pattern.compile(rgxAssignment);
                    Matcher mcher = ptn.matcher(line);
                    mcher.matches();
                    String name = mcher.group(1).trim();
                    String roman = mcher.group(2).trim();
                    if(!transList.containsKey(name)){
                        transList.put(name, roman);
                    }
                }else if(flag.equals("credits")){
                    Pattern ptn = Pattern.compile(rgxCredits);
                    Matcher mcher = ptn.matcher(line);
                    mcher.matches();

                    //get currency name
                    currency = mcher.group(4).trim();

                    String[] trans = mcher.group(1).split(" ");
                    int transValue = getTransValue(trans);
                    String curr = mcher.group(2);
                    int credits = Integer.parseInt(mcher.group(3).trim());
                    double value = credits/transValue;
                    curryList.put(curr, value);
                    //System.out.println(curr+":"+value);
                }else if(flag.equals("howmany")){
                    Pattern ptn = Pattern.compile(rgxHowMany);
                    Matcher mcher = ptn.matcher(line);
                    mcher.matches();
                    //check currency
                    if(!currency.equals(mcher.group(1))){
                        System.out.println(Error);
                    }

                    String[] trans = mcher.group(2).split(" ");
                    int transValue = getTransValue(trans);

                    String curr = mcher.group(3).trim();
                    double value = 0;
                    for(String k:curryList.keySet()){
                        if(k.equals(curr)){
                            value = curryList.get(k);
                        }
                    }

                    double total = transValue*value;
                    if(total != 0){
                        System.out.println(mcher.group(2)+"is "+(long)total+" "+currency);
                    }

                }else if(flag.equals("howmuch")){
                    Pattern ptn = Pattern.compile(rgxHowMuch);
                    Matcher mcher = ptn.matcher(line);
                    mcher.matches();

                    String[] trans = mcher.group(1).split(" ");
                    int transValue = getTransValue(trans);
                    if(transValue!=0){
                        System.out.println(mcher.group(1)+"is "+transValue);
                    }
                }else{
                    System.out.println(Error);
                }

                line = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Trader.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(Trader.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(Trader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static String checkFlag(String s){
        String flag = Error;
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

    private static int getValueByRoman(String roman){
        switch(Character.toUpperCase(roman.charAt(0))){
            case 'I':
                return Roman.I.getValue();
            case 'V':
                return Roman.V.getValue();
            case 'X':
                return Roman.X.getValue();
            case 'L':
                return Roman.L.getValue();
            case 'C':
                return Roman.C.getValue();
            case 'D':
                return Roman.D.getValue();
            case 'M':
                return Roman.M.getValue();
            default:
                return 0;
        }
    }


    private static int getTransValue(String[] trans){
        int value = 0;
        List<String> romans = new ArrayList<String>();
        for(int i=0;i<trans.length;i++){
            if(!transList.keySet().contains(trans[i])){
                System.out.println("Invalid input detected!");
                return 0;
            }
            romans.add(transList.get(trans[i]));
        }
        List<Integer> src = new ArrayList<Integer>();
        for(String s : romans){
            src.add(getValueByRoman(s));
        }

        List<Integer> newSrc = doSubstract(src);;
        for(int i : newSrc){
            value+=i;
        }

        return value;
    }

    private static List<Integer> doSubstract(List<Integer> numbers)
    {
        int currentElement;

        for(int i = 0 ; i < numbers.size() -1; i++)
        {
            currentElement = numbers.get(i);
            if( currentElement < numbers.get(i+1))
            {
                numbers.set(i, -currentElement);
            }
        }
        return numbers;
    }

    //create Roman enum
    public enum Roman{
        I("I",1),
        V("V",5),
        X("X",10),
        L("L",50),
        C("C",100),
        D("D",500),
        M("M",1000);

        private String name;
        private int value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
        Roman(String name, int value){
            this.name = name;
            this.value = value;
        }
    }
}
