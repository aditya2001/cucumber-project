package com.practice;

public class Reverseword {

    public static void main(String[] args) {
        String str = "This is your program";
        String[] words = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words){

            for (int i=word.length()-1; i>=0; i-- ){
                char ch = word.charAt(i);
                sb.append(ch);
            }
            sb.append(" ");
        }
        System.out.println(sb);

    }
}
