package com.practice;

import org.junit.Assert;

public class CheckPalindrome {

    public static void main(String[] args){

        String str = "aba";
        StringBuilder sb = new StringBuilder();

        for (int i=str.length()-1; i>=0; i--){
            char ch = str.charAt(i);
            sb.append(ch);

        }
        System.out.println(sb);
        System.out.println(sb.toString().equals(str));
    }
}
