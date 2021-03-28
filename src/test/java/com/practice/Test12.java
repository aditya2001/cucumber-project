package com.practice;

public class Test12 {

    public static void main(String[] args) {
int a= 3;
int b = 0;
        System.out.print("a");
        try {

            int c = a / b;
            System.exit(0);

        }
        catch(NumberFormatException e){

        }
        
        finally {

            System.out.println("done");
        }
        }


    }

