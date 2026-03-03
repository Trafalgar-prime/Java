package com.example.book;

import java.util.TreeSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        //go(); // con static uso questo
        new Main().go(); // senza static uso questo
    }

    public void go() { //mettere o togliere lo static mi cambia tutto

        Book b1 = new Book("Il nome della rosa","Umbero Eco", 50 );
        Book b2 = new Book("Se questo è un uomo","Primo Levi", 150 );
        Book b3 = new Book("One piece","Eichiro Oda", 250 );

        Set<Book> books = new TreeSet<>(); // per comparare le stringhe devo fare implements sulla classe Book
        books.add(b1);
        books.add(b2);
        books.add(b3);
        IO.println(books);

    }
}
