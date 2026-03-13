package com.example.battleships;

import java.io.*;
import java.util.ArrayList;

public class Game implements Serializable {
    private static final String SER_PATH = "data/game.ser";
    String username = "";
    int numOfGuesses = 0;
    ArrayList<Ship> ships;

    public static Game getInstance() {
        Game game = readObject();
        if (game == null || game.ships.isEmpty()) {
            game = new Game();
            game.ships = new GameBoard().placeShips();
        }

        return game;
    }

    private void welcome() {
        IO.println(
                "***************Welcome to the Battleships game!**************"
        );

        while (username.isEmpty()) {
            username = IO.readln("Come ti chiami? ").trim();
        }

        IO.println("Ciao " + username + ", prova a colpire le mie navi.");
        if (numOfGuesses > 0) {
            IO.println("Hai già effettuato "
                    + numOfGuesses + " tentativi e ci sono ancora "
                    + ships.size() + " navi.");
        }
        IO.println("Per sparare, inserisci le coordinate da a1 a g7.");
        IO.println("Esempio: A5");
        IO.println("Per chiudere il gioco, premi Q");
    }

    public void play() {
        welcome();

        while (!ships.isEmpty()) {
            String result = "acqua";
            String guess = IO.readln("spara: ");

            if (guess.equalsIgnoreCase("q")) {
                IO.println("OK. Ciao " + username);
                saveObject();
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
        saveObject();
    }

    private void saveObject() {
        try {
            FileOutputStream fs = new FileOutputStream(SER_PATH);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(this);
            os.close();
        } catch (FileNotFoundException e) {
            IO.println("Il file " + SER_PATH + " non è accessibile.");
        } catch (IOException e) {
            IO.println("Errore IO: " + e.getMessage());
        }
    }

    private static Game readObject() {
        Game game = null;

        try {
            FileInputStream fs = new FileInputStream(SER_PATH);
            ObjectInputStream os = new ObjectInputStream(fs);
            game = (Game) os.readObject();
        } catch (FileNotFoundException e) {
            IO.println("Il file " + SER_PATH + " non è accessibile.");
        } catch (IOException e) {
            IO.println("Errore IO: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            IO.println("Errore nella creazione dell'oggetto: " + e.getMessage());
        }

        return game;
    }
}
