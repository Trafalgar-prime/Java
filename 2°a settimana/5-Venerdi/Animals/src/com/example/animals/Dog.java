package com.example.animals;

public class Dog extends Wolf implements Pet {
    private static final int MIN_SIZE = 10;
    private static final String DEFAULT_NAME = "Fido";
    private static final int MAX_SIZE = 100;

    public Dog() {
        IO.println("Dog constructor");
    }

    public Dog(int size, String name) {
        super(size, name);
//        setSize(size);
//        setName(name);
//        IO.println(
//                "Ho creato un cane di nome "
//                        + getName() + " di dimensione " + getSize()
//        );
    }

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
