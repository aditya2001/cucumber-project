package com.practice;// File

import java.io.File;


public class testfile {

    public static void main(String[] args) {


            String folder = "C:\\Users\\adity\\Test";

            File fl = new File(folder);

            String[] str = new String[2];
            int i=0;
            File[] files = fl.listFiles();
            for(File file :files){
                str[i] = file.getAbsolutePath();
                i++;

            }

        }
    }