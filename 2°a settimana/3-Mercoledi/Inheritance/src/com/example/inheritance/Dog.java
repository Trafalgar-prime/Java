package com.example.inheritance;

public class Dog extends Canine{

    private void bark(){
        if (getSize() > 60){
            IO.println("woof");
        }else if(getSize() > 14){
            IO.println("Ruff");
        }else {
            IO.println("Yip");
        }
    }
    @Override
    public void eat(){
        IO.println(getName() + "  mangia carne in scatola");
    }

    @Override
    public void makeNoise(){
        bark();
    }
    public void sleep(){
        IO.println("Zzzzzzzzzzz");
    }

    //@Override qui non funziona perchè è un overload e non un override
    public void eat(int quantity){
        IO.println(getName() + " mangia " + quantity +  " etti di carne");
    }

}