package com.example.animals;

abstract public class Animal {
    private String name;
    private String picture;
    private int size = 1;
    private String food;
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size > 9) {
            this.size = size;
        }
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public abstract void eat();

    public void makeNoise() {
        IO.println("Un verso a caso");
    }

    public void sleep() {
        IO.println("Zzzzz");
    }

    public void roam() {
        IO.println("A zonzo per la foresta...");
    }

    public abstract void play();

}
