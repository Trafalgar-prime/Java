package com.example.animals;

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

        for (int i = 0; i<dogs.length; i++){
            dogs[i].setSize(new Random().nextInt(100));
            IO.println("Il cane "
                        + dogs[i].getName()
                        + " ha una taglia di : "
                        + dogs[i].getSize());
            dogs[i].bark();
        }

    }
}
