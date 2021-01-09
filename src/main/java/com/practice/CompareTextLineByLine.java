package com.practice;

import Utils.Lib;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompareTextLineByLine {

    public static void main(String[] args) {
        String folder1 = "C:\\Users\\adity\\OneDrive\\Desktop\\data\\Compare\\Actual";
        String folder2 = "C:\\Users\\adity\\OneDrive\\Desktop\\data\\Compare\\Expected";

            try {
                compare(folder1, folder2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public static void compare(String folder1, String folder2) throws IOException {
        Map<String, String> ActualFile = getFiles(folder1);
        Map<String, String> ExpectedFile = getFiles(folder2);

            String[] strArr= Lib.extractKeys(ActualFile);
            for(String a : strArr) {

                String fileName1 = ActualFile.get(a);
                String fileName2 = ExpectedFile.get(a);
                FileReader fr1 = new FileReader(fileName1);
                BufferedReader br1 = new BufferedReader(fr1);

                FileReader fr2 = new FileReader(fileName2);
                BufferedReader br2 = new BufferedReader(fr2);

                String lineFile1 = br1.readLine();
                String lineFile2 = br2.readLine();


            while(lineFile1 !=null || lineFile2 !=null) {
                      if(!lineFile1.equalsIgnoreCase(lineFile2)){
                          System.out.println(fileName1 +"not equals" + fileName2);
                          break;

                      }
                      else {
                          lineFile1 = br1.readLine();
                          lineFile2 = br2.readLine();
                          System.out.println("Equals");
                      }
            }


            }
        }




    public static Map<String, String> getFiles(String folderName) {
        List<String> fileNames = new ArrayList<>();
        HashMap<String, String> hm = new HashMap<>();
        File folder = new File(folderName);
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isAbsolute()) {
                String fileName = fileEntry.getAbsolutePath().substring(fileEntry.getAbsolutePath().lastIndexOf("-"));
                hm.put(fileName, fileEntry.getAbsolutePath());


            }
        }
        return hm;
    }




}
