package com.example.animals;

import java.lang.reflect.Array;

public class Main {
    public static void main(String[] args) {
        testPets();
    }

    public static void test1() {
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
            d.makeNoise();
            d.roam();
            d.eat(2);
            d.sleep();
        }
    }

    public static void testArrayPol() {
        Animal[] animals = new Animal[3];
        animals[0] = new Dog();
        animals[0].setName("Fido");
        animals[1] = new Cat();
        animals[1].setName("Tom");
        animals[2] = new Hippo();
        animals[2].setName("Minni");

        for (Animal a : animals) {
            a.makeNoise();
            a.roam();
            a.eat();
            a.sleep();
            a.eat();
        }
    }

    public static void testPets() {
        Pet[] animals = new Pet[2];
        animals[0] = new Dog();
        animals[1] = new Cat();

        for (Pet a : animals) {
            a.beFriendly();
        }
    }
}
