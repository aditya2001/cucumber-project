package com.practice;

import java.util.HashMap;
import java.util.Map;

public class DistinctCharsCount {
    public static void main(String[] args){

        String str = "aditya";
        Map<Character, Integer> hm = new HashMap<>();

        for(int i=0; i<=str.length()-1; i++){
            char ch = str.charAt(i);

            if(!hm.containsKey(ch)){
                hm.put(ch, 1);
            }
            else{
                int c = hm.get(ch);
                hm.put(ch, c+1);
            }
        }
        System.out.println(hm);


    }
}
