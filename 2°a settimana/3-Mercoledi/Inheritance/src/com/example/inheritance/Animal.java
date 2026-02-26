package com.example.inheritance;

public class Animal {
    private String name;
    private String picture;
    private int size;
    private String food;
    private String location;

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
        this.size = size;
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

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void eat(){
        IO.println("Gnam Gnam ....");
    }

    public void makeNoise(){
        IO.println("Un verso a caso");
    }

    public void roam(){
        IO.println("A zono per la foresta....");
    }
}
