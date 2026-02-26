package com.example.animals;

public class Main {
    public static void main(String[] args) {
        Dog dog1 = new Dog();
        dog1.setName("Bart");
        dog1.setSize(20);

        Dog[] dogs = new Dog[3];

        dogs[0] = dog1;
        dogs[1] = new Dog();
        dogs[2] = new Dog();

        dogs[1].setName("Fred");
        dogs[1].setSize(70);
        dogs[2].setName("Marge");

        for (Dog d : dogs) {
            d.bark();
        }
    }
}
