package com.javamastery.ch02_oop.ch01_classes_and_objects.employee;

import java.time.LocalDate;

public class EmployeeTest {
    public static void main(String[] args) {
        Employee[] staff = new Employee[5];

        staff[0] = new Employee("Carl Hooper", "Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Kane", "Hacker",50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Adams", "Tester", 40000, 1990, 3, 15);

        // from Java 10
        var christy = new Employee("Christy John", "CEO", 1000000, 1983, 5, 29);
        staff[3] = christy;

        // checking requireNonNull from Objects
        var unknown = new Employee(null, null, 0, 1982, 12, 1);
        staff[4] = unknown;

        // raise every one's salary by 5%
        for (Employee e: staff)
            e.raiseSalary(5);

        // print out information about all employees
        for (Employee e : staff)
            System.out.println(e);
    }
}
