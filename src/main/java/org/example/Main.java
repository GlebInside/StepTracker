package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        StepTracker stepTracker = new StepTracker();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to StepTracker");

        while (true) {
            System.out.println("\n Choose the option: \n 1 - Save steps of one day \n 2 - Print monthly statistics \n 3 - Change steps goal \n 4 - Turn the app off");

            var choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("Insert Month: ");
                    var m = scanner.next();
                    System.out.println("Insert day: ");
                    var d = scanner.nextInt();
                    System.out.println("Insert steps: ");
                    var s = scanner.nextInt();
                    stepTracker.stepSavePerDay(m, d, s);
                }
                case 2 -> {
                    System.out.println("Insert Month: ");
                    var m = scanner.next();
                    stepTracker.printStats(m);
                }
                case 3 -> {
                    System.out.println("Insert new step goal: ");
                    var newGoal = scanner.nextInt();
                    stepTracker.setStepGoal(newGoal);
                }
                case 4 -> {
                    scanner.close();
                    return;
                }
            }
        }
    }
}