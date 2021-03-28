//Program to find count of every word in a string
package com.practice;

import java.util.HashMap;
import java.util.Map;

public class WordCountInString {

    public static void main(String[] args){

        String str = "This is my first program program program is first mine";
        Map<String,Integer> hm = new HashMap<>();
        String[] strArr = str.split(" ");
        int count = 0;
        for (String a:strArr) {
           if (!hm.containsKey(a)){
               hm.put(a, 1);
           }
           else {
               hm.put(a, hm.get(a)+1);
            }
        }
        System.out.println(hm);

    }

}


