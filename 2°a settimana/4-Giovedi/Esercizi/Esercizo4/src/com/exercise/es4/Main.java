package com.exercise.es4;

public class Main {
    static void main() {
        int [] voto = new int[4];
        voto[0] = 6;
        voto[1] = 8;
        voto[2] = 7;
        voto[3] = 9;
        int j=0;
        for (int i = 0; i < voto.length; i++) {
            if (voto[i] >=6) {
                j++;
            }
        }
        switch (j){
            case 0 -> IO.println("Bocciato");
            case 1 -> IO.println("Bocciato");
            case 2 -> IO.println("Bocciato");
            case 3 -> IO.println("Bocciato");
            case 4 -> IO.println("Promosso");
        }


        int somma = 0;
        for (int i = 0; i < voto.length; i++) {
            somma += voto[i];
        }
        float media = (float) somma /voto.length;
        if (media >= 8){
            IO.println("Promosso per la seconda volta");
        }



        int z=0;
        for (int i = 0; i < voto.length; i++) {
            if (voto[i] >=6) {
                z++;
            }
        }
        switch (j){
            case 0 -> IO.println("Bocciato");
            case 1 -> IO.println("Bocciato");
            case 2 -> IO.println("Promosso per la terza volta");
            case 3 -> IO.println("Promosso per la terza volta");
            case 4 -> IO.println("Promosso per la terza volta");
        }
    }
}
