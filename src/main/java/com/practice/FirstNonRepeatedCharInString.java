package com.practice;

import java.awt.*;
import java.util.*;
import java.util.List;

public class FirstNonRepeatedCharInString {

    public static void main (String [] args){
        String str = "aditya";
        Map<Character,String> hm = new LinkedHashMap<>();
        List<Character> ls = new LinkedList<>();

        for (int i=0; i<=str.length()-1; i++){
            char ch = str.charAt(i);
            if(!hm.containsKey(ch)){
                hm.put(ch, "one");
            }
            else{
                hm.put(ch, "duplicate");
                hm.remove(ch, "duplicate");
            }
        }
                   System.out.println(hm);

    }
}
