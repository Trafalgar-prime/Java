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
    }
}
