package com.practice;

import java.util.Scanner;

public class ReplaceVowelsWithUsingReplace {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter String");
        String newString = "";
        String str = sc.nextLine();

        str.replace('a', '_');
        str.replace('i', '_');
    }
}
