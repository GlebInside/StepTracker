package org.example;

public class Converter {

    protected Double stepsToKilometers(Integer steps) {
        double cm = steps * 75;
        return cm / 100 / 1000;
    }

    protected Double stepsToKilocalories(Integer steps) {
        double cal = steps * 50;
        return cal / 1000;
    }
}
