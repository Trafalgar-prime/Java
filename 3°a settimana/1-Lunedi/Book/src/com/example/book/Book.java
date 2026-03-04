package com.example.book;

public class Book implements Comparable<Book>{
    private int year;
    private String title;
    private String author;
    private double price;

    public Book( String title, String author, int year, double price) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;

    }

    public int getYear() { return year; }

    public void setYear(int year) {this.year = year; }

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

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    @Override
    public int compareTo(Book o) { // per fare la comparazione tra stringhe; mi torna un intero per comparare poi le iniziali delle stringhe
        return title.compareTo(o.title);
    }

    @Override
    public String toString() { // per stampare a schermo
        return  String.format(
                "%s (%s, %d) - €%.2f",title,author,year,price
        );
        //return "Book{" + "title=" + title + ", author=" + author + ", year=" + year + ", price=" + price + "};\n";
    }


}
