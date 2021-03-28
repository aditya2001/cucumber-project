package com.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class UniqueChars {

    public static void main (String args[])
    {
        boolean result=false;
        String inputstring="Alive";
        System.out.println("String method 2 answer "+ method2(inputstring));
    }

    public static boolean method2(String input)
    {
        for(int i=0; i < input.length();i++)
        {
            char charcterofinputstring=input.charAt(i);
            int count=0;
            for(int j=i; j < input.length();j++)
            {
                if (charcterofinputstring==input.charAt(j))
                    count++;
            }
            if(count > 1)
                return false;
        }
        return true;
    }
}
