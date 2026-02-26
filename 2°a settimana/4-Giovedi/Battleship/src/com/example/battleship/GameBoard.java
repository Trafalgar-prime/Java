package com.example.battleship;

import java.util.ArrayList;
import java.util.Random;

public class GameBoard {
    private static final String ALPHABET =  "abcdefg";
    private static final int GRID_LENGTH = ALPHABET.length();
    private static final int SHIP_SIZE  = GRID_LENGTH / 2;
    private static final int SHIP_COUNT = SHIP_SIZE;
    private static final int COORD_RANGE = GRID_LENGTH - SHIP_SIZE + 1;

    private final Random random = new Random();
    private ArrayList<Ship> ships = new ArrayList<>();

    private ArrayList<String> createCells(){
        boolean isHorizontal = random.nextBoolean();
        int x = random.nextInt(COORD_RANGE) +1;
        int y = random.nextInt(COORD_RANGE);
        ArrayList<String> cells = new ArrayList<>();

        for(int i = 0; i < SHIP_SIZE; i++){
            String cell;

            if(isHorizontal){
                cell = "" + ALPHABET.charAt(y) + (x+i);
            }else {
                cell = "" + ALPHABET.charAt(y+i) + (x);
            }
            cells.add(cell);
        }
        return cells;

    }


    private boolean fit(ArrayList<String> cells){
        for (Ship ship : ships) {
            ArrayList<String> locationCells = ship.getLocationCells();
            for (String cell : cells) {
                if(locationCells.contains(cell)){
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<Ship> placeShips(){
        for (int i = 0; i < SHIP_COUNT; i++) {
            ArrayList<String> cells;

            do {
                cells = createCells();
            }  while (!fit(cells));

            Ship ship = new Ship();
            ship.setLocationCells(cells);
            ships.add(ship);

        }
        return ships;
    }
}
