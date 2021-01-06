package com.practice;

import java.util.Scanner;

public class ReplaceVowelswith {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter String");
        StringBuilder sb = new StringBuilder();
        String str = sc.nextLine();
         for (int i=0; i<=str.length()-1; i++){
             char ch = str.charAt(i);
             if (ch == 'a' || ch == 'e' || ch == 'i' || ch == '0' || ch == 'u') {
                 sb.append("_");
             }
                 else {
                     sb.append(ch);
                 }
             }
         System.out.println(sb.toString());
         }

         }

