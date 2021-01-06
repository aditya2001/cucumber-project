package com.practice;

import java.util.Scanner;

public class CountOfVowels {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter String");
        String newString = "";
        String str = sc.nextLine();
         int count =0;
        for (int i=0; i<=str.length()-1; i++) {
            char ch = str.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == '0' || ch == 'u') {
               count++;
            }


        }
        System.out.println(count);
    }
}
