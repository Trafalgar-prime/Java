package com.example.battleship;

import java.util.ArrayList;

public class ShipTestDrive {
    static void main() {
        Ship ship = new Ship();
        ArrayList<String> locations = new ArrayList<>();
        locations.add("A2");
        locations.add("A3");
        locations.add("A4");
        ship.setLocationCells(locations);

        String userGuess = "A2";
        String result = ship.check(userGuess);
        String testResult = "Failed";

        if (result.equals("Colpita")) {
            testResult = "Passed";
        }

        IO.println(result);
        IO.println(testResult);
    }
}
