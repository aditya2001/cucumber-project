package com.practice;

import java.util.ArrayList;
import java.util.List;

public class RemoveDuplicateCharFromString {

    public static void main(String[] args){
        String str = "aditya choudhary";
        StringBuilder sb = new StringBuilder();
        List<Character> ls = new ArrayList<>();
        for (int i =0; i<=str.length()-1; i++){
            char ch =str.charAt(i);
           if (!ls.contains(ch)){
               ls.add(ch);
               sb.append(ch);
           }
        }
        System.out.println(sb.toString());
    }
}
