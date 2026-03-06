package com.exercise.Es1;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayTest  {
    public  static void main(String[] args) {
        int[] arr = {1, 3, 7, 13, 9, 11, 5, 8};
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] %2 == 0){
                IO.println("Stampo il valore del'array: " + arr[i]);
                System.out.println("Stampo l'indice i se il valore dell'array è pari: " + i);
                System.out.println("Stampo l'indice i se il valore dell'array è pari(con indice da 1): " + (i + 1));
            }
            if(arr[i] > max){
                max = arr[i];
            }
        }
        IO.println("Stampo il maggiore della lista: " + max);

        List<Integer> lista = List.of(1, 3, 7, 13, 9, 11, 5, 8);
        IO.println("Stampo il maggiore della lista: " + lista.stream().max(Integer::compare).orElse(null));
        IO.println("\n");
        List lista_ordinata = lista.stream().distinct().sorted().toList();
        IO.println("qualcosa: " + lista_ordinata.get(lista_ordinata.size()-2));




        IO.println("Secondo valore max: " + lista.stream().sorted().distinct().skip(lista.size()-2).findFirst().orElse(null));

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                }
            }
        }
        IO.println("Stampo il secondo maggiore della lista: " + arr[arr.length - 2]);
    }
}

