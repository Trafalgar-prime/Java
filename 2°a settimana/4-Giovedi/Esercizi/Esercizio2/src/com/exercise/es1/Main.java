package com.exercise.es1;

public class Main {
    static void main() {
        int points = Integer.valueOf(IO.readln("Inserisci il valore: "));
        new ConteggioPoints().CountPoints(points);
    }
}
