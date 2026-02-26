package com.example.inheritance;

abstract public class Canine extends Animal{
    @Override
    public void roam() {
        super.roam();
    }

    abstract public void eat(); // devo assolutamente implementarla nella classe figlia

}
