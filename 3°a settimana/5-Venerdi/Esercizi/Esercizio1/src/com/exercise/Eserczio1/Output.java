package com.exercise.Eserczio1;

public class Output {

    void metodo1(int scanner){
        for (int i = 1; i <= scanner ; i++){
            for (int j = 1; j <= i; j++){
                IO.print(i);
            }
            IO.print("\n");
        }
    }

    void metodo2(int scanner){
        for(int i = 1; i <= scanner ; i++){
            int z = 1;
            for (int j = 0; j < i; j++){
                IO.print(z);
                z++;
            }
            IO.print("\n");
        }
    }

    void metodo3(int scanner){
        for(int i = 1; i <= scanner ; i++){
            for (int j = 1; j <= i; j++){
                IO.print(j);
            }
            IO.print("\n");
        }
    }
}
