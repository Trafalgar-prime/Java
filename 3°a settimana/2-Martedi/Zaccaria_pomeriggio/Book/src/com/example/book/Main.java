package com.example.book;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        new Main().go();
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
}
