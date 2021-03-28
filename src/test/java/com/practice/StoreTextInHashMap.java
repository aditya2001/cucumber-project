package com.practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class StoreTextInHashMap {

    public static void main(String[] args) throws IOException {

        FileReader fr = new FileReader("C:\\Users\\adity\\OneDrive\\Desktop\\data\\doc2.txt");
        BufferedReader br = new BufferedReader(fr);
        String line=br.readLine();
        StringBuilder sb = new StringBuilder();
        HashMap<String, String> hm = new HashMap<>();
        String ls = System.getProperty("line.separator");
        while(line!=null){

            String[] ary = line.split(":");
            int i =0;
            hm.put(ary[i], ary[i+1]);
            System.out.println(hm);
            line= br.readLine();
        }

    }
}
