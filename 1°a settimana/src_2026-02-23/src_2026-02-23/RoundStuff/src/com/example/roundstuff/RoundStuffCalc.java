package com.example.roundstuff;

public class RoundStuffCalc {
    public static final String UNIT_MEASURE = "m";

    public static double area(double radius) {
        return Math.PI * radius * radius;
    }

    public static double volume(double radius) {
        return ((4.0/3.0) * Math.PI * radius * radius * radius);
    }
}
