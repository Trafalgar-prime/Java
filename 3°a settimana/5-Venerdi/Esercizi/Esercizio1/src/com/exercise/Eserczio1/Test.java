package com.exercise.Eserczio1;

import java.util.Scanner;

public class Test {
    public  static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Inserisci il tuo valore: ");
        int valore = sc.nextInt();

        Output output = new Output();
        output.metodo1(valore);
        output.metodo2(valore);
        output.metodo3(valore);



    }
}
