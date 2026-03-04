package com.example.jukebox;

public class Song implements Comparable<Song> {
    private String title;
    private String artist;
    private int bpm;

    public Song(String title, String artist, int bpm) {
        this.title = title;
        this.artist = artist;
        this.bpm = bpm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    @Override
    public String toString() {
        return "Titolo: " + title + ", Autore: " + artist;
    }

    @Override
    public int compareTo(Song o) {
        return title.compareTo(o.title);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Song) {
            Song other = (Song) obj;
            return title.equals(other.getTitle());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
