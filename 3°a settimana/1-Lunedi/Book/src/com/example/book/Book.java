package com.example.book;

public class Book implements Comparable<Book>{
    private int page;
    private String title;
    private String author;

    public Book( String title, String author, int page) {
        this.title = title;
        this.author = author;
        this.page = page;

    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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


    @Override
    public int compareTo(Book o) { // per fare la comparazione tra stringhe; mi torna un intero per comparare poi le iniziali delle stringhe
        return title.compareTo(o.title);
    }

    @Override
    public String toString() { // per stampare a schermo
        return "Book{" + "title=" + title + ", author=" + author + ", page=" + page + "}\n";
    }
}
