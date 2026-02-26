package com.example.fruits;

import java.util.Random;

public class Apple {
	public int volume = 0;
	public String color = "red";
	public boolean Mature = false;
	
	public void grow(int increment) {
		volume += increment;
	}
	
	public String fall() {
		return "Cadooo!";
	}

	public void checkColor(){
		if(color.equals("red")){
			IO.println("Questa è una mela rossa");
		}else if(color.equals("green")){
			IO.println("Questa mela è verde");
		}else {
			IO.println("Questa mela ha il colore: " + color);
		}
	}
	public void describeApple(){
		switch (color){
			case "red":
				IO.println("Questa è una mela rossa");
				break;
			case "green":
				IO.println("Questa mela è verde");
				break;
			default:
				IO.println("Questa mela ha il colore: " + color);
				break;
		}	
	}
	public void mature(){
		if (volume > 20){
			IO.println("La mela è matura");
			fall();
		}
	}

	public void growToMaxVolume(int maxVolume) {
		while (maxVolume > volume) {
			grow(2);
			IO.println("Questo è il volume attuale: " + volume);
		}
		IO.println("La mela ha raggiunto il volume massimo: " + volume);
		IO.println(fall());;
		Mature = true;
	}

	public void eatApple(){
		if (Mature){
			do { 
				volume -= 3;
				IO.println("Hai mangiato un pezzo di mela, il volume attuale è: " + volume);	
			} while (volume > 0);
		}else{
			IO.println("La mela non è matura, non puoi mangiarla!");
		}
		IO.println("La mela è finita!");
	}

	public void growSlowly(int incrementTo) {
		int maxVolume = incrementTo-volume;
		for (int i=0; i < maxVolume; i++){
			grow(1);
			IO.println("Questo è il volume attuale: " + volume);
		}
		IO.println("La mela ha raggiunto il volume massimo: " + volume);
		if (maxVolume > 30){
			IO.println("La mela è troppo grande, cade! " + fall());
			Mature = true;

		}
	}

	public static void GenerateRandomApples(){   
		String[] colors = {"red", "green", "yellow"};
        Apple[] apples = new Apple[10];
        for (int i = 0; i < apples.length; i++) {
           int color = new Random().nextInt(colors.length); //mi ritorna un intero che va da 0 a 2, che corrisponde agli indici dell'array colors
           int volume = new Random().nextInt(20) + 1; //mi ritorna un intero che va da 1 a 20, che corrisponde al volume della mela
           apples[i] = new Apple();
           apples[i].color = colors[color];
           apples[i].volume = volume;
           IO.println("La mela " + (i+1) + " è di colore " + apples[i].color + " e ha un volume di " + apples[i].volume);
		}
	}
}
