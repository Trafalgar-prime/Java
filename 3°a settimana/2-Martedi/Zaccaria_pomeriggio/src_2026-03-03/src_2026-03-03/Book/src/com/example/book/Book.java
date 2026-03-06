package com.example.book;

public class Book implements Comparable<Book> {
    private String title;
    private String author;
    private int year;
    private double price;

    public Book(String title) {
        this.title = title;
    }

    public Book(String title, String author, int year, double price) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format(
                "%s (%s, %d) - €%.2f", title, author, year, price
        );
    }

    @Override
    public int compareTo(Book o) {
        return title.compareTo(o.getTitle());
    }
}
