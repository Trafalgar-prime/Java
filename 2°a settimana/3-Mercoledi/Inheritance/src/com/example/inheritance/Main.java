package com.example.inheritance;

import java.util.Random;

public class Main {
    static void main(){
        Dog dog1 = new Dog();
        dog1.setName("Bart");

        Dog[] dogs = new Dog[3];

        dogs[0] = dog1;
        dogs[1] = new Dog();
        dogs[2] = new Dog();

        dogs[1].setName("Fred");
        dogs[2].setName(("Marge"));

        dogs[1].setSize(new Random().nextInt(100));
        IO.println(dogs[1].getSize());

        for (int i = 0; i<dogs.length; i++){
            dogs[i].setSize(new Random().nextInt(100));
            IO.println("Il cane "
                        + dogs[i].getName()
                        + " ha una taglia di : "
                        + dogs[i].getSize());
            dogs[i].makeNoise();
            dogs[i].eat();
        }

        Cat[] cats = new Cat[3];

        IO.println("\n");

        for (int i = 0; i<cats.length; i++){
            cats[i] = new Cat();
            cats[i].setName(String.valueOf(new Random().nextInt(10)));
            cats[i].setSize(new Random().nextInt(100));
            IO.println("Il gatto si chiama: "
                        + cats[i].getName()
                        + " ha una taglia di : "
                        + cats[i].getSize());
            cats[i].makeNoise();
            cats[i].eat();
            cats[i].roam();
            IO.println("\n");

        }

        Animal[] animals = new Animal[5];
        animals[0] = new Dog();
        animals[1] = new Cat();
        animals[2] = new Dog();
        animals[3] = new Cat();
        animals[4] = new Dog();

        for (int i = 0; i<animals.length; i++){
            animals[i].setName(String.valueOf(new Random().nextInt(10)));
            animals[i].setSize(new Random().nextInt(100));
            IO.println("L'animale si chiama: "
                    + animals[i].getName()
                    + " ha una taglia di : "
                    + animals[i].getSize());
            animals[i].makeNoise();
            animals[i].eat();
            animals[i].roam();
            //animals[i].sleep();  //non funziona perche sleep si trova dentro Dog e non dentro Animal
            IO.println("\n");
        }

        //animals[0].eat(10);  questo non funziona perchè non si trova in Animal ma in Dog
        dogs[0].eat(10);

    }
}
