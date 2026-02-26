package com.example.fruits;

public class Main {
    static void main2() {

        Apple apple = new Apple();
        apple.volume = 1;
        int volume = apple.volume;

        System.out.println("La mela ha un volume pari a " + volume);
        // IO.println("La mela ha un volume pari a " + volume); // vedi nota sotto
        apple.grow(10);
        System.out.println("La mela ora ha un volume pari a " + apple.volume);

        String appleMessage = apple.fall();
        System.out.println(appleMessage);

        Egg eggs = new Egg();
        double eggNumber = eggs.eggNumber(0.86);
        // IO.println("Il numero di uova(double) è: " + eggNumber);
        int intEggNumber = eggs.intEggNumber(0.86);
        // IO.println("Il numero di uova(int) è: " + intEggNumber);

        int castEggNumber = (int) eggNumber;
        // IO.println("Il numero di uova(cast) è: " + castEggNumber);

        System.out.println("Il numero di uova(double) è: " + eggNumber);
        System.out.println("Il numero di uova(int) è: " + intEggNumber);
        System.out.println("Il numero di uova(cast) è: " + castEggNumber);

    }
    static void main() {
        Apple apple1 = new Apple();
        Apple apple2 = new Apple();
        Apple apple3 = new Apple();
        apple1.color = "red";
        apple2.color = "green";
        apple3.color = "yellow";
        apple1.checkColor();
        apple2.checkColor();
        apple3.checkColor();

        int numero = 7;
        String risultato = (numero % 2 == 0) ? "Pari" : "Dispari"; //è un sistema ternario, se la condizione è vera restituisce "Pari", altrimenti "Dispari"; alternativa all'if-else
        System.out.println(risultato);

        apple1.describeApple();
        apple2.describeApple();
        apple3.describeApple();

        apple1.grow(8);
        apple1.mature();
        apple2.grow(17);
        apple2.mature();
        apple3.grow(25);
        apple3.mature();

        Apple apple4 = new Apple();
        apple4.grow(5);
        IO.println("Il volume della mela è: " + apple4.volume);
        apple4.growToMaxVolume(25);
        IO.print("\n");
        apple4.eatApple();
        IO.print("\n");
        apple3.eatApple();
        IO.print("\n");

        apple1.grow(7);
        IO.println("Il volume della mela è: " + apple1.volume);
        apple1.Mature = true;
        apple1.eatApple();

        Apple apple5 = new Apple();
        apple5.grow(3);
        apple5.growSlowly(34);
        apple5.eatApple();
        IO.print("\n");

        Apple.GenerateRandomApples();


    }


}
