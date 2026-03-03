package com.exercise.es3;

public class Main2 {
    public static void main(String[] args) {

        Allievo2 studente  = new Allievo2();

        int[] voti = {7,9,8,9};

        studente.Check(voti);
        studente.Mean(voti);
        studente.Alternative(voti);

    }
}
