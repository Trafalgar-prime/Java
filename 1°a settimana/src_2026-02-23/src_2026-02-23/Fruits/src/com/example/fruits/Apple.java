package com.example.fruits;

public class Apple {
    public int volume = 0;
    public String color = "green";

    public void grow(int increment) {
        volume += increment;
    };

    public String fall() {
        return "Cadooo!";
    }
}
