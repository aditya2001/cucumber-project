package com.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Occurence {
    Map<Character, Integer> hm = new HashMap();

    public static void main(String[] arags){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text");
        String str = sc.next();
        Occurence oc = new Occurence();

        for (int i=0; i<=str.length()-1; i++){
            char ch = str.charAt(i);

            if (!oc.hm.containsKey(ch)){
                oc.hm.put(ch, 1);
            }
            else {
                oc.hm.put(ch, oc.hm.get(ch)+1);
            }
        }
       System.out.println(oc.hm);
    }


}
