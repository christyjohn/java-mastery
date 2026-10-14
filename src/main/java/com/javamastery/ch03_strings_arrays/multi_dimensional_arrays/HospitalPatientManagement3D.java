package com.javamastery.ch03_strings_arrays.multi_dimensional_arrays;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Scenario: Hospital Patient Monitoring System
 * Imagine you're developing a small monitoring system for a hospital.
 * The hospital has multiple wards, each ward has several rooms, and each room has multiple beds.
 * You need to record the temperature of patients at different beds.
 *
 * A 3-D array is a natural way to represent this data.
 * Dimension 1 - Ward, Dimension 2 - Room, Dimension 3 - Bed
 *
 * 1. Define the problem
 * ======================
 * Suppose the hospital has:
 * - 2 wards
 * - 3 rooms per ward
 * - 4 beds per room
 * Each bed has a patient's temperature reading.
 *
 * 2. Your programming challenges
 * ==============================
 *
 * Challenge 1 — Populate the array (Easy)
 * Use nested loops to assign temperatures to every bed.
 * Start with manually entered values or generate sample values for practice.
 *
 * Challenge 2 — Display all readings (Easy)
 * Print every reading grouped by ward, room, and bed. Format the output so the hierarchy is clear.
 *
 * Challenge 3 — Calculate the average (Medium)
 * Calculate the average temperature for each ward, then calculate the average across the entire hospital.
 *
 * Challenge 4 — Detect high readings (Medium)
 * Find all beds where the temperature exceeds a configurable threshold, such as 38.0°C.
 * Print each matching ward, room, bed, and reading.
 *
 * Challenge 5 — Find the maximum (Medium)
 * Identify the highest recorded temperature in the entire hospital and its exact location.
 *
 * Challenge 6 — Change the dimensions (Hard)
 * Modify your program to accept the number of wards, rooms, and beds at runtime instead of hardcoding them.
 */
public class HospitalPatientManagement3D {
    public static void main(String[] args) {
        int wards = 2;
        int roomsPerWard = 3;
        int bedsPerWard = 4;

        // Challenge 6 — Change the dimensions
        Scanner in = new Scanner(System.in);
        System.out.println("How many wards does you hospital have? ");
        wards = in.nextInt();
        System.out.println("How many rooms are there per ward? ");
        roomsPerWard = in.nextInt();
        System.out.println("How many beds are there in each room? ");
        bedsPerWard = in.nextInt();

        final double high_temperature = 38.0;

        double[][][] temperatures = new double[wards][roomsPerWard][bedsPerWard];

        //Random random = new Random();

        // Challenge 1 : Populate the array
        for (int i = 0; i < temperatures.length; i++) {
            for (int j = 0; j < temperatures[i].length; j++) {
               for (int k = 0; k < temperatures[i][j].length; k++) {
                    temperatures[i][j][k] = ThreadLocalRandom.current()
                           .nextInt(3500, 4201) / 100.0;
                    // may also use - but not exactly 2 fractional points
                   //temperatures[i][j][k] = 35.00 + random.nextDouble() * (42.00 - 35.00);

               }
            }
        }

        // Challenge 2 : Display all readings
        for (int i = 0; i < temperatures.length; i++) {
            System.out.println("Ward " + (i + 1));
            for (int j = 0; j < temperatures[i].length; j++) {
                System.out.print("Room " + (j + 1) + " : ");
                for (int k = 0; k < temperatures[i][j].length; k++) {
                    System.out.print(temperatures[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        // or use enhanced for
        /*for (double[][] ward : temperatures) {
            for (double[] room : ward) {
                for (double temperature : room) {
                    System.out.print(temperature + " ");
                }
                System.out.println();
            }
            System.out.println();
        }*/

        // Challenge 3: Calculate average temperatures for the hospital and each ward

        double sumTemperatureInHospital = 0.0;
        int totalBedsInHospital = 0;

        for (int i = 0; i < temperatures.length; i++) {

            double sumTemperatureInWard = 0.0;
            int totalBedsInWard = 0;

            for (int j = 0; j < temperatures[i].length; j++) {
                for (int k = 0; k < temperatures[i][j].length; k++) {

                    double temperature = temperatures[i][j][k];

                    // Accumulate hospital-wide statistics
                    sumTemperatureInHospital += temperature;
                    totalBedsInHospital++;

                    // Accumulate current ward's statistics
                    sumTemperatureInWard += temperature;
                    totalBedsInWard++;
                }
            }

            // Print one average per ward
            System.out.printf(
                    "Average temperature for Ward %d: %.2f%n",
                    i + 1,
                    sumTemperatureInWard / totalBedsInWard
            );
        }

        // Print hospital-wide average
        double averageTemperatureInHospital =
                sumTemperatureInHospital / totalBedsInHospital;

        System.out.printf(
                "Average temperature for overall hospital: %.2f%n",
                averageTemperatureInHospital
        );

        // Challenge 4 — Detect high readings
        for (int i = 0; i < temperatures.length; i++) {
            for (int j = 0; j < temperatures[i].length; j++) {
                for (int k = 0; k < temperatures[i][j].length; k++) {
                    if (temperatures[i][j][k] > high_temperature) {
                        System.out.println("High temperature monitored at: Ward-" +
                                (i+1) + ", Room-" + (j+1) + ", Bed-" + (k+1) +
                                " Temp -> " + temperatures[i][j][k]);
                    }
                }
            }
        }

        // Challenge 5 — Find the maximum
        double highestTemperature = temperatures[0][0][0];

        int highestWard = 0;
        int highestRoom = 0;
        int highestBed = 0;

        for (int i = 0; i < temperatures.length; i++) {
            for (int j = 0; j < temperatures[i].length; j++) {
                for (int k = 0; k < temperatures[i][j].length; k++) {
                    if (temperatures[i][j][k] > highestTemperature) {
                        highestTemperature = temperatures[i][j][k];

                        highestWard = i;
                        highestRoom = j;
                        highestBed = k;
                    }
                }
            }
        }

        System.out.printf(
                "Highest temperature: %.2f°C at Ward-%d, Room-%d, Bed-%d%n",
                highestTemperature, highestWard, highestRoom, highestBed
        );
    }
}
