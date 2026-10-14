package com.javamastery.ch03_strings_arrays.single_dimensional_arrays;

public class BasicArraysOperations {
    public static void main(String[] args) {

        int[] rollNums = new int[3];

        // int[] arrays are initialized to 0
        for (int i : rollNums)
            System.out.print(i + " "); //0 0 0
        System.out.println();

        /*
        rollNums[0] = 101;
        rollNums[1] = 102;
        rollNums[2] = 103;

        // int[] rollNums = { 101, 102, 103 };

        System.out.println(rollNums[0]);
        System.out.println(rollNums[1]);
        System.out.println(rollNums[2]);
        */

        int x = 101;
        for(int i=0; i < rollNums.length; i++) {
            rollNums[i] = x;
            x++;
        }

        for(int i=0; i < rollNums.length; i++) {
            System.out.println(rollNums[i]);
        }

        System.out.println(rollNums.length);
    }
}
