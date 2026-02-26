package com.example.battleships;

import java.util.ArrayList;

public class Game {
    String username = "";
    int numOfGuesses = 0;
    ArrayList<Ship> ships;

    private void welcome() {
        IO.println(
                "***************Welcome to the Battleships game!**************"
        );

        while (username.isEmpty()) {
            username = IO.readln("Come ti chiami? ").trim();
        }

        IO.println("Ciao " + username + ", prova a colpire le mie navi.");
        IO.println("Per sparare, inserisci le coordinate da a1 a g7.");
        IO.println("Esempio: A5");
        IO.println("Per chiudere il gioco, premi Q");
    }

    public void play() {
        welcome();

        ships = new GameBoard().placeShips();

        while (!ships.isEmpty()) {
            String result = "acqua";
            String guess = IO.readln("spara: ");

            if (guess.equalsIgnoreCase("q")) {
                IO.println("OK. Ciao " + username);
                return;
            }

            numOfGuesses++;

            for (Ship ship : ships) {
                result = ship.check(guess);

                if (!result.equals("acqua")) {
                    if (result.equals("affondata")) {
                        ships.remove(ship);
                    }
                    break;
                }
            }
            IO.println(result);
        }
        IO.println("Hai vinto dopo " + numOfGuesses + " tentativi");
    }
}
