package com.exercise.utente;

import java.io.*;

public class File {

    public static void writeFile(String filename, Utente utente) throws IOException {
        try {
            // Terza modalità
            // File scritto in append + metodo append + a capo

            FileWriter file_scrittura = new FileWriter(new java.io.File(filename), false);
            file_scrittura.write("\n14 - Questa è una prova di scrittura di file.\n");

            file_scrittura.write(utente.getUsername() + ";" + utente.getPassword());
            file_scrittura.write("\n");
            file_scrittura.write("16 - Questa è una terza riga");

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
        try {
            FileInputStream file_lettura = new FileInputStream(filename);

            int carattere_letto = file_lettura.read();
            System.out.println(carattere_letto);
            System.out.print((char)carattere_letto);

            file_lettura.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
