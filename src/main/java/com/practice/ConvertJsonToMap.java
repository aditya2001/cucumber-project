package com.practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConvertJsonToMap {

    public static void main(String[] args) throws IOException {

        FileReader fr = new FileReader("C:\\Users\\adity\\workspace\\Test\\order-1.json");
        BufferedReader br = new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(ls);


        }
            System.out.println(sb.toString());

    }
}
