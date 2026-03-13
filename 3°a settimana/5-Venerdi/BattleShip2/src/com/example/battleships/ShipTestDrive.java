package com.example.battleships;

import java.util.ArrayList;

public class ShipTestDrive {
    public static void main(String[] args) {
        Ship ship = new Ship();
        ArrayList<String> locations = new ArrayList<>();
        locations.add("A2");
        locations.add("A3");
        locations.add("A4");
        ship.setLocationCells(locations);
        String userGuess = "A2";
        String result = ship.check(userGuess);
        String testResult = "failed";

        if (result.equals("colpita")) {
            testResult = "passed";
        }

        System.out.println(result);
        System.out.println(testResult);
    }
}
