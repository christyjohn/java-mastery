package com.javamastery.ch01_fundamentals.datatypes;

public class FloatsDemo {
    public static void main(String[] args) {
        float f1 = 8.125f;
        float f2 = 0.7f;

        System.out.printf("%.20f\n", f1); // 8.12500000000000000000
        System.out.println(f1); // 8.125
        System.out.printf("%.20f\n", f2); // 0.69999998807907100000
        System.out.println(f2); // 0.7

        byte b1 = -0;
        byte b2 = 0;
        System.out.println(b1); // 0
        System.out.println(b2); // 0
    }
}
