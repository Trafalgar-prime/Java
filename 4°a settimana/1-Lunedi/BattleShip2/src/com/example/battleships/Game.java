package com.example.battleships;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Game implements Serializable {
    private static final String LOG_PATH = "data/game.log";
    private static final String JSON_PATH = "data/game.json";
    private static final String SER_PATH = "data/game.ser";
    public String username = "";
    public int numOfGuesses = 0;
    public ArrayList<Ship> ships;

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
                //saveObject();
                saveJson();
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
        close();


    }

    private void close(){
        IO.println("Hai vinto dopo " + numOfGuesses + " tentativi");
        //saveObject();
        saveJson();

        saveWinnerLog();

        String response = IO.readln("Vuoi leggere i punteggi? (Y/N)");
        if (response.equalsIgnoreCase("Y")) {
            readWinnerLog();
        }
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

    private void saveJson(){
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File(JSON_PATH), this);
        } catch (StreamWriteException e) {
            IO.println("Errore stream writing: " + e.getMessage());
        } catch (DatabindException e) {
            IO.println("Errore databind: " + e.getMessage());
        } catch (IOException e) {
            IO.println("Errore IO: " + e.getMessage());
        }
    }

    private static Game readJson() {
        Game game = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            game = mapper.readValue(new File(JSON_PATH), Game.class);
        } catch (StreamReadException e) {
            IO.println("Il file " + JSON_PATH + " non è accessibile.");
        } catch (DatabindException e) {
            IO.println("Errore databind: " + e.getMessage());
        } catch (IOException e) {
            IO.println("Errore IO: " + e.getMessage());
        }

        return game;
    }

    private void saveWinnerLog(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOG_PATH,true))){
            bw.write(LocalDateTime.now().toString() + " - " + username + ": " + numOfGuesses);
        } catch (IOException e) {
            IO.println("Errore IO: " + e.getMessage());
        }
    }

    private void readWinnerLog(){
        try (BufferedReader br = new BufferedReader(new FileReader(LOG_PATH))){
            String line;
            while ((line = br.readLine()) != null) {
                IO.println(line);
            }
        } catch (FileNotFoundException e) {
            IO.println("Errore IO: " + e.getMessage());
        } catch (IOException e) {
            IO.println("Errore IO: " + e.getMessage());
        }
    }


}
