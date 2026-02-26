package com.example.battleship;

import java.util.ArrayList;

public class Ship {

    private ArrayList<String> locationCells;

    public ArrayList<String> getLocationCells() {
        return locationCells;
    }

    public void setLocationCells(ArrayList<String> locationCells) {
        this.locationCells = locationCells;
    }

    public String check(String guess){
        String result = "Acqua";
        if(locationCells.contains(guess)){
            locationCells.remove(guess);
            if(locationCells.isEmpty()){
                result = "Affondata";
            }else {
                result = "Colpita";
            }
        }
        return result;
    }
}
