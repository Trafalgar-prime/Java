package com.example.animals;

public class Cat extends Feline implements Pet {
    private static final int MIN_SIZE = 5;
    private static final String DEFAULT_NAME = "Felix";
    private static final int MAX_SIZE = 100;

    public Cat() {
        this(MIN_SIZE, DEFAULT_NAME);
    }

    public Cat(String name) {
        this(MIN_SIZE, name);
    }

    public Cat(int size) {
        this(size, DEFAULT_NAME);
    }

    public Cat(int size, String name) {
        setSize(Math.max(Math.min(size, MAX_SIZE), MIN_SIZE));
        setName(name);
        IO.println(
                "Ho creato un gatto di nome "
                        + getName() + " di dimensione " + getSize()
        );
    }

    @Override
    public void eat() {
        IO.println(getName() + " mangia cibo per gatti e insetti.");
    }

    @Override
    public void makeNoise() {

        IO.println(getName() + " dice Miao.");
    }

    @Override
    public void play() {
        IO.println(getName() + " gioca con il gomitolo.");
    }

    @Override
    public void beFriendly() {
        IO.println(getName() + " fa le fusa.");
    }

    @Override
    public void sleep() {
        super.sleep();
        IO.println("Conto i topini...");
    }
}
