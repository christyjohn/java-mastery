package com.javamastery.ch02_oop.ch01_classes_and_objects.employee;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {

    private String name;
    private String jobTitle;
    private double salary;
    private LocalDate hireDay;

    public Employee(String name, String jobTitle, double salary, int year, int month, int day) {
        // tough love - throws NullPointerException
        this.name = Objects.requireNonNull(name, "Unknown");
        //this.jobTitle = Objects.requireNonNull(jobTitle, "Intruder");

        // accepts with default
        //this.name = Objects.requireNonNullElse(name, "Unknown");
        this.jobTitle = Objects.requireNonNullElse(jobTitle, "Intruder");
        this.salary = salary;
        hireDay = LocalDate.of(year, month, day);
    }

    public String getName() {
        return name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary* byPercent / 100;
        salary += raise;
    }

    @Override
    public String toString() {
        return "[name = " + this.getName() +
                ", title = " + this.getJobTitle() +
                ", salary = " + this.getSalary() +
                ", hired on = " + this.getHireDay() + "]";
    }
}
