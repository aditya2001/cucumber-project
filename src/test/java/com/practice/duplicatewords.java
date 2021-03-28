package com.practice;// Find no of vowels in the String

import java.util.HashMap;
import java.util.Scanner;

public class duplicatewords {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter String");
        String str = sc.nextLine();
        HashMap<String, String> hm = new HashMap<>();
        String[] words= str.split(" ");


        for (String word : words) {
            if (!hm.containsKey(word)){
                hm.put(word,"1");
            }
            else{
                hm.put(word, "duplicate");
            }
        }
        System.out.println(hm);

    }

}