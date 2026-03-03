package com.example.animals;

public class Hippo extends Animal {
    @Override
    public void makeNoise() {
        IO.println(getName() + " grugnisce.");
    }

    @Override
    public void eat() {
        IO.println(getName() + " mangia i fiori.");
    }

    @Override
    public void play() {
        IO.println(getName() + " gioca con il fango.");
    }
}
