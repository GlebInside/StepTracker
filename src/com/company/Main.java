package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {


    static void welcomeMsg() {
        System.out.println("\nChoose option # from menu below:");
        System.out.println("1. Save steps for one day");
        System.out.println("2. Get stats for one month");
        System.out.println("3. Change your monthly goal");
        System.out.println("4. Shutdown");
    }

    public static void main(String[] args) throws IOException {
        StepTracker stepTracker = new StepTracker();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to StepTracker!");

        while (true) {
            welcomeMsg();
            int choice = scan.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("Insert data: Month");
                    String m = reader.readLine();
                    System.out.println("Insert data: Day");
                    int d = scan.nextInt();
                    System.out.println("Insert data: Steps");
                    int s = scan.nextInt();
                    stepTracker.saveSteps(m, d, s);
                }
                case 2 -> {
                    System.out.println("Insert data: Month");
                    String month = reader.readLine();
                    stepTracker.printStats(month);
                }
                case 3 -> {
                    stepTracker.getTarget();
                    System.out.println("Insert new goal: ");
                    int newGoal = Integer.parseInt(reader.readLine());
                    stepTracker.setTarget(newGoal);
                }
                case 4 -> {
                    System.out.println("See you next time!");
                    System.exit(0);
                }
                default -> System.out.println("Can't recognize, try again");
            }
        }
    }
}
