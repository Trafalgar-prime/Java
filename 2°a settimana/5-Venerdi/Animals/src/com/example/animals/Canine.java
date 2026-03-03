package com.example.animals;

abstract public class Canine extends Animal {
    @Override
    public void roam() {
        IO.println("Corro per i prati...");
    }
}
