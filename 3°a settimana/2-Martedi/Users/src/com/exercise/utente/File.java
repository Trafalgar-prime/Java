package com.exercise.utente;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class File {

    public static void writeFile() {
        try {
            // Terza modalità
            // File scritto in append + metodo append + a capo

            FileWriter file_scrittura = new FileWriter(new java.io.File("C:\\temp\\fileprovascrittura.txt"), false);
            file_scrittura.write("\n14 - Questa è una prova di scrittura di file.");

            file_scrittura.write("\n15 - dove scriverà?");
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
}
