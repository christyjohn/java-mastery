package com.javamastery.ch03_strings_arrays.two_dimensional_arrays;

public class SimpleMatrixExample {
    public static void main(String[] args) {

        //int[][] marks = new int[3][3];

        // int[][] marks = new int[3][3];  // rows, cols
        // marks[0][0] = 23;
        // marks[0][1] = 25;
        // marks[0][2] = 45;

        // marks[1][0] = 34;
        // marks[1][1] = 11;
        // marks[1][2] = 90;

        // marks[2][0] = 56;
        // marks[2][1] = 23;
        // marks[2][2] = 78;

        // 2-D array
        int[][] marks = {
                {12, 14, 56},
                {34, 45, 67},
                {45, 67, 78}
        };

        for(int row = 0; row < marks.length; row++) {
            for(int col = 0; col < marks[row].length; col++) {
                System.out.print(marks[row][col] + " ");
            }
            System.out.println();
        }

    }
}
