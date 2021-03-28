package com.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrLop2 {

    public static void main(String[] args){

        List<String> arrList = new ArrayList<>();

        System.out.println(arrList.isEmpty());

        arrList.add("adi");
        arrList.add("mina");
        arrList.add("bear");
//        arrList.remove(1);
//        arrList.set(0, "Hyna");

        List<String> arrList2 = new ArrayList<>();
        arrList.add("mina");
        arrList.add("adi");
        arrList.add("bear");

        System.out.println(arrList.equals(arrList2));

        Collections.sort(arrList);
//        Collections.sort(arrList, Collections.reverseOrder());
        Collections.reverse(arrList);

        System.out.println(arrList);
        System.out.println(arrList.size());

    }
}
