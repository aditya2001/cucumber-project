package com.practice;

public class Chars {


    public static void main (String[] args){
        String str = "aditya ads";
        int count = 0;
        for (int i=0; i<=str.length()-1; i++){
            char ch = str.charAt(i);
            if (ch !=' ' && ch != '.' && ch !='-'){
                count++;
            }
        }
        System.out.println(count);

    }
}


