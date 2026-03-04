package com.exercise.utente;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main() throws IOException {
        new Main().go();
    }

    public static void go() throws IOException {
        Utente utente1 = new Utente("user1", "password1");
        Utente utente2 = new Utente("user2", "password2");
        Utente utente3 = new Utente("user3", "password3");

        ArrayList<Utente> utenti = new ArrayList<>();
        utenti.add(utente1);
        utenti.add(utente2);
        utenti.add(utente3);

        File file = new File();
        file.writeFile("filename.txt", utenti);
        file.readFile("filename.txt");

    }
}
