package com.example.animals;

public class Cat extends Feline implements Pet {
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
}
