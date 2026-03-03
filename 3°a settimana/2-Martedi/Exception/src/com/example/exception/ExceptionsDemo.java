package com.example.exception;

public class ExceptionsDemo {
    public static void main(String[] args) {

        int var_dividendo = 50;
        int var_divisor = 0;

        // 1) Questo NON lancia ArithmeticException: stai solo sommando.
        // Per testare davvero, devi dividere.
        try {
            int risultato = var_dividendo / var_divisor; // qui scatta l'eccezione
            System.out.println(risultato);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
            IO.println(e.getMessage()); // <-- se vuoi stampare davvero il messaggio
        }

        // 2) Array fuori limite
        try {
            int[] numeri = new int[3];
            System.out.println(numeri[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Indice fuori limite!");
        } finally {
            System.out.println("Blocco terminato.");
        }

        // 3) Catch in ordine corretto
        try {
            int x = 10 / 0;
            System.out.println(x);
        } catch (ArithmeticException e) {
            System.out.println("Divisione per zero!");
        } catch (Exception e) {
            System.out.println("Errore generico");
        }
    }
}