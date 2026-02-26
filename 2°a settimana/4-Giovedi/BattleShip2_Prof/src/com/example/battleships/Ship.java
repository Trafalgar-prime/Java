package com.example.battleships;

import java.util.ArrayList;

public class Ship {
    private ArrayList<String> locationCells;

    public ArrayList<String> getLocationCells() {
        return locationCells;
    }

    public void setLocationCells(ArrayList<String> locationCells) {
        this.locationCells = locationCells;
    }

    public String check(String guess) {
        String result = "acqua";

        if (locationCells.contains(guess)) {
            locationCells.remove(guess);
            if (locationCells.isEmpty()) {
                result = "affondata";
            } else  {
                result = "colpita";
            }
        }

        return  result;
    }
}
