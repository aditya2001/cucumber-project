package com.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DuplicateCharactersInString {

    public static void main (String[] args){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the String");
        String str = sc.nextLine();
        char[] charArr = str.toCharArray();
        Map<Character, String> hm = new HashMap<>();

        for (char ch : charArr ){
            if (!hm.containsKey(ch)){
                hm.put(ch, "one");
            }
            else{
                hm.put(ch, "duplicate");
            }
        }
      System.out.println(hm);
    }
}
