package com.example.RoundStuff;

public class RoundStuffCalc {

    public static final String  UNIT_MEASURE = "m";

    public static double area(double ray){
        return Math.PI * ray * ray;
    }

    public double volume(double ray){
        return (4.0/3.0)*Math.PI*ray+ray*ray;
    }
}
