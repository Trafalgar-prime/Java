package com.example.battleship;

import java.util.ArrayList;

public class Game {
    String username = "";
    int numberOfGuesses = 0;
    ArrayList<Ship> ships;

    private void welcome() {
        IO.println("Welcome to Battleship!");

        while(username.isEmpty()) {
            username = IO.readln("Come ti chaimi? ").trim();  //trim elimina gli spazi vuoti tra i caratteri
        }

        IO.println("Ciao " + username + ", prova a colpire le mie navi");
        IO.println("Per sparere, inserisci le coordinate da a1 a g7");
        IO.println("Esempio: A5");
        IO.println("Per chiudere il gioco, premi Q");
        IO.println("");
    }

    public void play(){
        welcome();

        ships = new GameBoard().placeShips();

        while (!ships.isEmpty()) {
            String result = "Acqua";
            String guess = IO.readln("Spara: ");

            if (guess.equalsIgnoreCase("q")) {
                IO.println("OK. Ciao " + username);
                return;
            }

            numberOfGuesses++;

            for (Ship ship : ships) {
                result = ship.check(guess);

                if (!result.equals("Acqua")) {
                    if (result.equals("Affondata")) {
                        ships.remove(ship);
                    }
                    break;
                }
            }
            IO.println(result);
            IO.print("\n");
        }
        IO.println("Hai vinto dopo " + numberOfGuesses + " tentativi");
    }
}
