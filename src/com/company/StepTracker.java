package com.company;

import java.util.*;

public class StepTracker {

    private HashMap<String, ArrayList<Integer>> storage;
    private int target = 10000;

    private final ArrayList<String> months = new ArrayList<>(Arrays.asList("January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"));


    public StepTracker() {

        initStorage();
    }


    private void initStorage() {
        storage = new HashMap<>();
        for (String month : months) {
            storage.put(month, initDefaultArray());
        }
    }

    private ArrayList<Integer> initDefaultArray() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            arrayList.add(0);
        }
        return arrayList;
    }

    public void getTarget() {
        System.out.println("Current goal is: " + target + " steps");
    }

    public void setTarget(int target) {
            this.target = target;
            System.out.println("New goal has been set: " + target + " steps");
        }

    public void printStats(String month) {
        if (!storage.containsKey(month))
            System.out.println("Wrong month, sorry :(");
        else {
            ArrayList<Integer> arrayList = storage.get(month);
            getStats(month, arrayList);
            getMonthTotal(month, arrayList);
            getMonthMax(month);
            getMonthAverage(month);
            getMonthTotalDistance(month);
            getMonthCalories(month);
            getBestSeries(month);
        }
    }

    public void saveSteps(String month, int day, int steps) {
        if (!storage.containsKey(month))
            System.out.println("Wrong month, sorry :(");
        else if (day > 30)
            System.out.println("No more days left in this month, try next one!");
        else if (steps < 0)
            System.out.println("Steps amount cannot be less than 0");
        else {
            ArrayList<Integer> arrayList = storage.get(month);
            arrayList.set(day - 1, steps);

            System.out.println("Saved " + steps + " steps on " + month + " " + day);
        }
    }

    private void getStats(String month, ArrayList<Integer> arrayList) {

        for (int i = 0; i < 30; i++) {
            if (i == 29) {
                System.out.print(i + 1 + " день: " + arrayList.get(i));
                return;
            }

            System.out.print(i + 1 + " день: " + arrayList.get(i) + ", ");
        }
        System.out.println();
    }


    private void getMonthTotal(String month, ArrayList<Integer> arrayList) {
        int total = 0;
        for (Integer i : arrayList) {
            total += i;
        }

        System.out.println("\nTotal steps in " + month + ": " + total);

    }

    private void getMonthMax(String month) {
        ArrayList<Integer> arrayList = storage.get(month);
        int max = 0;
        max = Collections.max(arrayList);

        System.out.println("Max steps in " + month + ": " + max);
    }

    private void getMonthAverage(String month) {
        ArrayList<Integer> arrayList = storage.get(month);
        int avg = 0;

        for (Integer i : arrayList) {
            avg += i;
        }
        avg = avg / arrayList.size();

        System.out.println("Average steps in " + month + ": " + avg);

    }

    private void getMonthTotalDistance(String month) {
        ArrayList<Integer> arrayList = storage.get(month);
        int total = 0;
        for (Integer i : arrayList) {
            total += i;
        }

        double totalKm = (total * 0.75) / 1000;

        System.out.println("Total kilometers in " + month + ": " + totalKm);

    }

    private void getMonthCalories(String month) {
        ArrayList<Integer> arrayList = storage.get(month);
        int total = 0;
        for (Integer i : arrayList) {
            total += i;
        }

        int calories = Converter.convert(total);

        System.out.println("Total calories burned in " + month + ": " + calories);

    }

    private void getBestSeries(String month) {
        ArrayList<Integer> arrayList = storage.get(month);
        int best = 0;
        int count = 0;

        for (Integer integer : arrayList) {
            if (integer > target) {
                count++;
                if (count > best) {
                    best = count;
                }
            }

            if (integer <= target) {
                count = 0;
            }
        }

        System.out.println("Best streak in " + month + ": " + best + " days in a row!");
    }
}
