package com.practice;

public class TotalCharsInString {

    public static void main (String[] args){
        String str = "adityachoudhary";
        int count = 0;
        for (int i=0; i<str.length()-1; i++){
            char ch = str.charAt(i);
            if (ch !=' '  && ch != '.'){
                count++;
            }
        }
   System.out.println(count);

    }
}
