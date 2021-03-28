package com.practice;

public class Question6 {

    public static void main(String[] args){
        Test d1 = new Test();
        Test d2 = new Test();
        Test d3 = new Test();

        d1.printNumber();
        d2.printNumber();
        d3.printNumber();
    }
}

class Test{
    static int count;

    Test(){
        count++;
    }

    public void printNumber(){
        System.out.println(count);
    }
}
