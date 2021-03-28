package com.practice;

public class Reverseword {

    public static void main(String[] args) {
        String str = "This is your program";
        String[] words = str.split(" ");
//        StringBuilder sb = new StringBuilder();
        for (String word : words){
            String reverse ="";
            for (int i=word.length()-1; i>=0; i-- ){

                char ch = word.charAt(i);
                reverse = reverse + ch;

//                sb.append(ch);
            }
   System.out.println(reverse);
        }


    }
}
