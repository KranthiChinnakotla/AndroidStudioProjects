package com.example.tejakanchinadam.inclass03;

import java.util.Random;

public class Util {
    private static final String _NUM = "1234567890";
    private static final String _UCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String _LCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String _SYMB = "!@#$%/.";

    public static String getPassword(int length, boolean num, boolean upper, boolean lower, boolean special){

        Random rand = new Random();
        int SIZE = 0;

        if(length == 0) // generate random number between 8 and 12
            SIZE = rand.nextInt((12-8) + 1) + 8;
        else if(length == 1) // generate random number between 13 and 17
            SIZE = rand.nextInt((17-13) + 1) + 13;
        else //generate random number between 18 and 22
            SIZE = rand.nextInt((22-18) + 1) + 18;


        String CHAR_SET = "";

        if(num) CHAR_SET = CHAR_SET.concat(_NUM); //append numbers to character set

        if(upper) CHAR_SET =CHAR_SET.concat(_UCASE); //append uppercase

        if(lower) CHAR_SET =CHAR_SET.concat(_LCASE); //append lowercase

        if(special) CHAR_SET =CHAR_SET.concat(_SYMB); //append special characters

        if(CHAR_SET.isEmpty())
            return "";

        StringBuffer randStr = new StringBuffer();
        for(int i=0; i<SIZE; i++){ //using randomly generated size of password
            int randomIndex = getRandomIndex(CHAR_SET.length()); //get random index
            char ch = CHAR_SET.charAt(randomIndex); //select character at random index
            randStr.append(ch); //append selected character to password substring
        }
        return  randStr.toString(); //return password string
    }

    private static int getRandomIndex(int len){
        Random rand = new Random();
        int randomInt = 0;
        for(int i=0; i<10000; i++){
            for(int j=0; j<100;j++){
                randomInt = rand.nextInt(len);
            }
        }
        return randomInt;
    }

}
