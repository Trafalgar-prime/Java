package com.exercise.utente;

import java.io.*;

public class Main {
    public static void main() throws IOException {
        new Main().go();
    }

    public static void go() throws IOException {

        Utente utente1 = new Utente("user1", "password1");
        Utente utente2 = new Utente("user2", "password2");
        Utente utente3 = new Utente("user3", "password3");

        File file = new File();

        File.writeFile("filename.txt", utente1);
        IO.println("\n");
        file.readFile("filename.txt");
    }
}
