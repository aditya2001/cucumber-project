package com.practice;

import java.util.Scanner;

public class ReplaceVowelswithUnderscore {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter String");
        String newString = "";
        String str = sc.nextLine();

        char[] ary = str.toCharArray();
        int i = 0;


        System.out.println(ary);
    }
}
