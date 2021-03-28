package com.practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReplaceVowelsInAFile {

    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("C:\\Users\\adity\\workspace\\Test\\document.txt");
        BufferedReader br = new BufferedReader(fr);
        String line="";
        StringBuilder sb = new StringBuilder();
        String ls = System.getProperty("line.separator");
        while((line=br.readLine())!=null){


            for (int i=0; i<=line.length()-1; i++){
                char ch = line.charAt(i);
                if (ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u'){
                    sb.append("?");
                }
                else {
                    sb.append(ch);
                }
            }
            sb.append(ls);
        }
        System.out.println(sb.toString());


    }
}
