package com.practice;

public class Test1 {

    public static void main(String[] args){

        String str = "aditya";
        String newString="";
        for (int i=0; i<=str.length()-1; i++){
            char ch = str.charAt(i);
            if (ch !='i'){
                newString = newString +ch;
            }

        }
        System.out.println(newString);

    }
}
