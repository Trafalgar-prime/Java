package com.example.book;

import java.util.TreeSet;
import java.util.Set;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        //go(); // con static uso questo
        //new Main().go(); // senza static uso questo
        testStream();
    }

    public void go() { //mettere o togliere lo static mi cambia tutto

        Book b1 = new Book("Il nome della rosa","Umbero Eco", 1950, 17.98);
        Book b2 = new Book("Se questo è un uomo","Primo Levi", 1997 ,14.99);
        Book b3 = new Book("One piece","Eichiro Oda", 1997,32.50 );

        Set<Book> books = new TreeSet<>(); // per comparare le stringhe devo fare implements sulla classe Book
        books.add(b1);
        books.add(b2);
        books.add(b3);
        IO.println(books);

    }

    public static void testStream(){
        List<Book> books = BookStore.fetchBooks(); //posso chiamarlo senza chiamare una variabile perche il metodo è statico
        IO.println(books);

        //estrarre i libri publicati nel XIX secolo

        List<Book> xixBooks = books.stream()
                .filter(book -> book.getYear() > 1800 && book.getYear() < 1901)
                .toList();
        IO.println("\nLista di libri del XIX secolo: ");
        xixBooks.forEach(System.out::println);

        List<String> titles = books.stream()
                .map(book -> book.getTitle().toUpperCase())
                .toList();
        IO.println("\nTitoli in maiuscolo: ");
        titles.forEach(System.out::println);

        double totalPrice = books.stream()
                .mapToDouble(Book::getPrice)
                .sum();
        IO.println("\nPrezzo totalwe dei libri: " + totalPrice + "€");

        Book mostExpensiveBook = books.stream()
                .max(Comparator.comparing(Book::getPrice))
                .orElse(null);
        IO.println("\nIl libro più costoso é: " + mostExpensiveBook);

        //raggruppare i libri per autore
        Map<String, List<Book>> booksByAuthor = books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor));

        IO.println("\nLibri ordinati per autore: ");
        booksByAuthor.forEach((author , authorBooks) -> {
            System.out.println(author + ": " + authorBooks);
        });
    }
}
