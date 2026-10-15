package com.javamastery.ch02_oop.ch03_objects_deep_dive;

public class JavaIsPassByValue {
    public static void main(String[] args) {

        /*
         * test 1 - Methods can't modify numeric parameters
         */
        System.out.println("Testing tripleValue:");
        double percent = 10;
        System.out.println("Before: percent = " + percent);
        tripleValue(percent);
        System.out.println("After: percent = " + percent);

        /*
         * test 1 - Methods cancan changethe state of the object parameters
         */
        System.out.println("\nTesting tripleSalary:");
        var harry = new Employee("Harry", 5000);
        System.out.println("Before: salary = " + harry.getSalary());
        tripleSalary(harry);
        System.out.println("After: salary = " + harry.getSalary());

        /*
         * test 3 - Methods can't attach new objects to object parameters
         */
        System.out.println("\nTesting swap:");
        var a = new Employee("Alice", 70000);
        var b = new Employee("Bob", 600000);
        System.out.println("Before: a = " + a.getName());
        System.out.println("Before: b = " + b.getName());
        swap(a, b);
        System.out.println("After: a = " + a.getName());
        System.out.println("After: b = " + b.getName());
    }

    public static void tripleValue(double x) { // doesn't work
        x = x * 3;
        System.out.println("End of method x = " + x);
    }

    public static void tripleSalary(Employee x) { // works
        x.rasieSalary(200);
        System.out.println("End of method: salary = " + x.getSalary());
    }

    public static void swap(Employee x, Employee y) {
        Employee temp = x;
        x = y;
        y = temp;

        System.out.println("End of method: x = " + x.getName());
        System.out.println("End of method: y = " + y.getName());

    }

    public static class Employee {
        private String name;
        private double salary;

        public Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public double getSalary() {
            return salary;
        }

        public void rasieSalary(double byPercent) {
            double raise = salary * byPercent / 100;
            salary += raise;
        }
    }
}






