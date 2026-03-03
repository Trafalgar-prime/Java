package com.example.animals;

public class Wolf extends Animal {
    private static final int MIN_SIZE = 10;
    private static final String DEFAULT_NAME = "Alberto";
    private static final int MAX_SIZE = 100;

    public Wolf() {
        IO.println("Wolf constructor");
    }

    public Wolf(int size, String name) {
        setSize(Math.max(Math.min(size, MAX_SIZE), MIN_SIZE));
        setName(name);
        IO.println(
                "Ho creato "
                        + getName() + " di dimensione " + getSize()
        );
    }

    @Override
    public void eat() {

    }

    @Override
    public void play() {

    }
}
