package com.example.battleships;

import java.util.ArrayList;

public class GameBoardTestDrive {
    public static void main(String[] args) {
        ArrayList<Ship> ships = new GameBoard().placeShips();
        for (Ship ship : ships) {
            IO.println(ship.getLocationCells());
        }
    }
}
