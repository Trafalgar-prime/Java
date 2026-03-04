package com.example.book;

public class Book implements Comparable<Book> {
    private String title;

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public int compareTo(Book o) {
        return title.compareTo(o.getTitle());
    }
}
