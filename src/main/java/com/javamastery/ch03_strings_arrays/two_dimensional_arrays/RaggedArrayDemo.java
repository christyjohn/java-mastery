package com.javamastery.ch03_strings_arrays.two_dimensional_arrays;

import java.util.Arrays;

public class RaggedArrayDemo {
    public static void main(String[] args) {

        int[][] marks = new int[3][]; // rows = 3
        System.out.println(Arrays.deepToString(marks)); // [null, null, null]

        // marks[0] = new int[1];
        // marks[1] = new int[2];
        // marks[2] = new int[3];

        // marks[0][0] = 23;

        // marks[1][0] = 24;
        // marks[1][1] = 90;

        // marks[2][0] = 12;
        // marks[2][1] = 78;
        // marks[2][2] = 45;

        // /*
        //     [23]
        //     [24] [90]
        //     [12] [78] [45]

        int[][] marks2 = {
                { 23 },
                { 24, 90 },
                { 12, 78, 45 }
        };

        for(int row = 0; row < marks2.length; row++) {
            for(int col = 0; col < marks2[row].length; col++) {
                System.out.print(marks2[row][col] + " ");
            }
            System.out.println();
        }

        // Correct way to print a 2D array
        System.out.println(Arrays.deepToString(marks2)); // [[23], [24, 90], [12, 78, 45]]
    }
}
