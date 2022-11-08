package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;

public class StepTracker {

    private final Map<String, Map<Integer, Integer>> stat = new TreeMap<>();
    private int stepGoal = 10000;

    Converter converter = new Converter();

    public void setStepGoal(int stepGoal) {
        this.stepGoal = stepGoal;
    }

    protected void stepSavePerDay(String month, int day, int steps) {
        if (day > 30) {
            throw new RuntimeException(" It's only 30 days in month");
        }
        if (steps < 0) {
            throw new RuntimeException("The number of steps has to be positive");
        }
        if (day < 1) {
            throw new RuntimeException("The day has to be 1 or more");
        }
        if (!stat.containsKey(month)) {
            stat.put(month, new TreeMap<>());
        }
        stat.get(month).put(day, steps);
    }

    protected Integer maxStepsPerMonth(String month) {
        if (stat.isEmpty()) {
            return 0;
        } else {
            System.out.println("Max amount of steps per " + month + ": " + stat.get(month).values().stream().max(Comparator.naturalOrder()).get());
            return stat.get(month).values().stream().max(Comparator.naturalOrder()).get();
        }
    }

    protected Integer allStepsPerMonth(String month) {
        int steps = 0;
        if (stat.isEmpty()) {
            return 0;
        }
        List<Integer> sum = stat.get(month).values().stream().toList();
        for (Integer step : sum) {
            steps += step;
        }
        System.out.println("All steps per " + month + ": " + steps);
        return steps;
    }

    protected Integer averageNumberOfStepsPerMonth(String month) {
        int steps = 0;
        if (stat.isEmpty()) {
            return 0;
        }
        List<Integer> sum = stat.get(month).values().stream().toList();
        for (Integer step : sum) {
            steps += step;
        }
        var a = steps / 30;
        System.out.println("Average number of steps per " + month + ": " + a);
        return a;
    }

    protected Integer bestSeries(String month) {
        int count = 0;
        int max = 0;
        if (stat.isEmpty()) {
            return 0;
        }
        var steps = stat.get(month).values().stream().toList();
        for (Integer step : steps) {
            if (step >= stepGoal) {
                count++;
                if (max < count) {
                    max = count;
                }
            }
            if (step < stepGoal && count > 0) {
                count = 0;
            }
        }
        System.out.println("The best series of days in " + month + ": " + max);
        return max;
    }

    protected void numberOfSteps(String month) {
        var steps = stat.get(month);
        if (stat.isEmpty()) {
            return;
        }
        for (int i = 1; i <= 30; i++) {
            if (steps.keySet().stream().filter(Predicate.isEqual(i)).toList().isEmpty()) {
                steps.put(i, 0);
            }
            if (i < 30) {
                System.out.print(steps.keySet().stream().filter(Predicate.isEqual(i)).toList().get(0) + " day: " + steps.get(i) + ", ");
            }
            if (i == 30) {
                steps.put(i, 0);
                System.out.print(steps.keySet().stream().filter(Predicate.isEqual(i)).toList().get(0) + " day: " + steps.get(i));
            }
        }
        System.out.println("\n");
    }

    protected Double kcalPerMonth(String month) {
        int steps = 0;
        if (stat.isEmpty()) {
            return 0.0;
        }
        List<Integer> sum = stat.get(month).values().stream().toList();
        for (Integer step : sum) {
            steps += step;
        }
        var kcal = converter.stepsToKilocalories(steps);
        System.out.println("Total amount of kcal per month: " + kcal);
        return kcal;
    }

    protected Double kmPerMonth(String month) {
        int steps = 0;
        if (stat.isEmpty()) {
            return 0.0;
        }
        List<Integer> sum = stat.get(month).values().stream().toList();
        for (Integer step : sum) {
            steps += step;
        }
        var km = converter.stepsToKilometers(steps);
        System.out.println("Total amount of km per month: " + km);
        return km;
    }

    protected void printStats(String m) {
        this.numberOfSteps(m);
        this.allStepsPerMonth(m);
        this.averageNumberOfStepsPerMonth(m);
        this.bestSeries(m);
        this.maxStepsPerMonth(m);
        this.kcalPerMonth(m);
        this.kmPerMonth(m);
    }
}
