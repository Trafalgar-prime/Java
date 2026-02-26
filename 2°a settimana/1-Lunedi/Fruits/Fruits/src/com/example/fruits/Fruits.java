package com.example.fruits;

public class Fruits {

	static void main() {
		
		Apple apple = new Apple();
		apple.volume = 1;
		int volume = apple.volume;
		
		System.out.println("La mela ha un volume pari a " + volume);
		
		apple.grow(10);
		
		System.out.println("La mela ora ha un volume pari a " + apple.volume);
		
		String appleMessage = apple.fall();
		
		System.out.println(appleMessage);

	}


}
