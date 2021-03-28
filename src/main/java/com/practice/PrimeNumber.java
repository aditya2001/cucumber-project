package com.practice;

import java.util.Scanner;

public class PrimeNumber {

    public static void main(String[] args){
       Scanner sc = new Scanner(System.in);
       System.out.println("Enter the number");
       int num = sc.nextInt();


           for (int i=2; i<num; i++){
               int ans = num % i;
               if (!(ans == 0)){
                   System.out.println("prime number");
                   break;
               }
               else{
                   System.out.println("Odd even number");
                   break;
               }
       }

    }
}
