package com.example.inheritance;

public class Cat extends Feline {

    @Override
    public void roam(){
        super.roam();
    }

    @Override
    public void eat(){
        IO.println(getName() + " mangia cibo");
    }

    @Override
    public void makeNoise(){
        IO.println(getName() + " fa Miao");
    }
}
