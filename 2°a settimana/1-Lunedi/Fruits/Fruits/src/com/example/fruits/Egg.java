package com.example.fruits;

public class Egg {
    public double eggPrice = 0.09;

    public double eggNumber(double budget){
        return budget/eggPrice;
    }

    public int intEggNumber(double budget){
        return (int) Math.round(budget/eggPrice);
    }
}
