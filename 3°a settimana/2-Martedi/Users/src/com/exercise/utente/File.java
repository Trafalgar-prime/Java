package com.exercise.utente;

import java.io.*;
import java.util.ArrayList;

public class File {

    public static void writeFile(String filename, ArrayList<Utente> utenti) throws IOException {
        try {
            // Terza modalità
            // File scritto in append + metodo append + a capo

            FileWriter file_scrittura = new FileWriter(new java.io.File(filename), false);
            for (Utente utente : utenti) {
                file_scrittura.write(utente.getUsername() + ";" + utente.getPassword() + "\n");
            }
            file_scrittura.close();

        } catch (FileNotFoundException e) {
            System.out.println("non va bene!!!" + e);
            //creazione directory
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void readFile(String filename) {
        // Prima modalità
        // lettura di file un carattere alla volta
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filename))) {

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
