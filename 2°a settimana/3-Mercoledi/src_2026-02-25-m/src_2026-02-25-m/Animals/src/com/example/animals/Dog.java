package com.example.animals;

public class Dog {
    private String name;
    private int size = 10;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size > 9) {
            this.size = size;
        }
    }

    public void bark() {
        if (size > 60) {
            IO.println(name + " dice Wooof!");
        } else if (size > 14) {
            IO.println(name + " dice Ruff!");
        } else {
            IO.println(name + " dice Yip!");
        }
    }

    public void eat() {
        // il cane mangia
    }

    public void sleep() {
        // il cane dorme
    }
}
