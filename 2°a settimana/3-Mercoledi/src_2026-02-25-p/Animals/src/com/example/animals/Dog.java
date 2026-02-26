package com.example.animals;

public class Dog extends Canine implements Pet {
    private void bark() {
        if (getSize() > 60) {
            IO.println(getName() + " dice Wooof!");
        } else if (getSize() > 14) {
            IO.println(getName() + " dice Ruff!");
        } else {
            IO.println(getName() + " dice Yip!");
        }
    }

    @Override
    public void eat() {
        IO.println(getName() + " mangia carne in scatola.");
    }

    @Override
    public void makeNoise() {
        bark();
    }

    @Override
    public void play() {
        IO.println(getName() + " gioca con la palla.");
    }

    public void howl() {
        IO.println(getName() + " ulula.");
    }

    public void eat(int quantity) {
        IO.println(getName() + " mangia " + quantity + " etti di carne");
    }

    @Override
    public void beFriendly() {
        IO.println(getName() + " fa le feste.");
    }
}
