package com.practice;

import java.util.ArrayList;

public class ArrLoop {

    ArrayList<String> arrList = new ArrayList<>();


        public static void main(String[] args){


            ArrLoop ar = new ArrLoop();


            System.out.println(ar.arrList);

            ar.arrList.add("jaguar");
            ar.arrList.add("lion");
            ar.arrList.add(1,"tiger");


//            System.out.println(ar.arrList);

            for (String a : ar.arrList){
                System.out.println(a);

            }
        }
}
