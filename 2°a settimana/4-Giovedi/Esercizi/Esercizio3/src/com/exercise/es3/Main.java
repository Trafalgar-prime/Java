package com.exercise.es3;

public class Main {
    public static void main(String[] args) {

        Allievo studente  = new Allievo();

        int[] voti = {7,9,8,9};

        studente.Result(studente.Check(voti));
        studente.Result(studente.Mean(voti));
        studente.Result(studente.Alternative(voti));

    }
}
