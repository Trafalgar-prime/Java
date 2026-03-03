package com.exercise.es3;

public class Allievo {

    public boolean Check(int[] voto){
        boolean promosso  = false;
        int j = 0;
        for (int i = 0; i < voto.length; i++) {
            if (voto[i] >= 6) {
                j++;
            }
        }
        if(j >= 4) {
            promosso = true;
        }else {
            ;
        }
        return promosso;
    }

    public boolean Mean(int[] voto){
        boolean promosso  = false;
        int somma = 0;
        for (int i = 0; i < voto.length; i++) {
            somma +=voto[i];
        }
        if((somma/ voto.length) >= 8){
            promosso = true ;
        }else {
            ;
        }
        return promosso;
    }

    public boolean Alternative(int[] voto){
        boolean promosso  = false;
        int z = 0;
        for (int i = 0; i < voto.length; i++) {
            if (voto[i] >= 6) {
                z++;
            }
        }
        switch (z){
            case 0,1 -> promosso = false;
            case 2,3,4 -> promosso = true;
        }
        return promosso;
    }

    public void Result(boolean promosso){
        if (promosso){
            IO.println("Promosso");
        }else  {
            IO.println("Bocciato");
        }
    }
}
