package com.example.battleship;

import java.util.ArrayList;

public class GameBoardTest {
    static void main() {
        ArrayList<Ship> ships = new GameBoard().placeShips();
        for (Ship ship : ships) {
            IO.println(ship.getLocationCells());
        }

    }
}
