package com.exercise.es3;

public class Allievo2 {

    public void Check(int[] voto) {
        int count = 0;
        for (int i = 0; i < voto.length; i++) {
            if (voto[i] >= 6) {
                count++;
            }
        }
        if (count >= 4) {
            IO.println("Promosso");
        } else {
            IO.println("Bocciato");
        }
    }

    public void Mean(int[] voto) {
        int somma = 0;
        for (int i = 0; i < voto.length; i++) {
            somma += voto[i];
        }
        if ((somma / voto.length) >= 8) {
            IO.println("Promosso");
        } else {
            IO.println("Bocciato");
        }
    }

    public void Alternative(int[] voto) {
        int count = 0;
        for (int i = 0; i < voto.length; i++) {
            if (voto[i] >= 6) {
                count++;
            }
        }
        switch (count) {
            case 0, 1 -> IO.println("Bocciato");
            case 2, 3, 4 -> IO.println("Promosso");
        }
    }
}