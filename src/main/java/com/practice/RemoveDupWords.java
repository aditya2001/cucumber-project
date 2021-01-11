package com.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RemoveDupWords {
    public static void main(String[] args){
        String str ="This is my first first program program";
        String[] words = str.split(" ");
        List<String> ls = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
       for (String word : words){
           if(!ls.contains(word)){
               ls.add(word);
               sb.append(word + " ");

           }
            }
       System.out.println(sb);
        }

}
