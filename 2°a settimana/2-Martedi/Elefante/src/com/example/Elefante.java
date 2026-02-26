package com.example;
import java.util.Scanner;

public class Elefante {
    public int numero;
    
    public int Inserimento(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserisci il numero intero di elefanti: ");
        return numero = sc.nextInt();
    }

    public void Incremento(){
        for (int i = 1; i < numero + 1; i++){
            if(i==1){
                IO.println( i + " elefante si dondolava sul filo di una ragnatela,");
                IO.println("  e trovando la cosa interessante, andò a chiamare un altro elefante.");
            }else{
                IO.println( i + " elefanti si dondolavano sul filo di una ragnatela,");
                IO.println("  e trovando la cosa interessante, andarono a chiamare un altro elefante.");
            }

        }
    }
}
