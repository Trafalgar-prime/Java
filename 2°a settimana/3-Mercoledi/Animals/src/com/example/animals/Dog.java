package com.example.animals;

public class Dog {
    private String name;
    private int size;

    public void bark(){
        if (size > 60){
            IO.println("woof");
        }else if(size > 14){
            IO.println("Ruff");
        }else {
            IO.println("Yip");
        }
    }

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
        this.size = size;
    }

    public void eat(){

    }

    public void sleep(){

    }


}
