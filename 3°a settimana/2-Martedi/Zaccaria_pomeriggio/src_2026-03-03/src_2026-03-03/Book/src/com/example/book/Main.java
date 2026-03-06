package com.example.book;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//        new Main().go();
        testStream();
    }

    public void go() {
        Book book1 = new Book("La lettera scarlatta");
        Book book2 = new Book("Io robot");
        Book book3 = new Book("La divina commedia");

        Set<Book> books = new TreeSet<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);

        System.out.println(books);
    }

    public static void testStream() {
        List<Book> books = BookStore.fetchBooks();
        System.out.println(books);

        // estrarre i libri pubblicati nel XIX secolo
        List<Book> xixBooks = books.stream()
                .filter(book -> book.getYear() > 1800 && book.getYear() < 1901)
                .toList();
        System.out.println("Libri pubblicati nel XIX secolo:");
        xixBooks.forEach(System.out::println);

        // estrarre i titoli formattandoli in maiuscolo
        List<String> titles = books.stream()
                .map(book -> book.getTitle().toUpperCase())
                .toList();
        System.out.println("Titoli in maiuscolo: " + titles);

        // sommare i prezzi dei libri
        double totalPrice = books.stream()
                .mapToDouble(Book::getPrice)
                .sum();
        System.out.println("Totale libri: €" + totalPrice);

        // trovare il libro più costoso
        Book mostExpensiveBook = books.stream()
                .max(Comparator.comparing(Book::getPrice))
                .orElse(null);
        System.out.println("Il libro più costoso è " + mostExpensiveBook);

        // raggruppare i libri per autore
        Map<String, List<Book>> booksByAuthor = books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor));
        System.out.println("Libri raggruppati per autore:");
        booksByAuthor.forEach(
                (author, authorBooks) -> {
                    System.out.println(author + ": " + authorBooks);
                }
        );
    }
}
