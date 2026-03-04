package com.example.jukebox;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        new Main().go();
    }

    public void go() {
        List<Song> songs = MockSongs.getSongs();
        System.out.println("Songs: " + songs);

//        Collections.sort(songs);

//        TitleComparator tititleComparatortleComparator = new TitleComparator();
        songs.sort(
                (one, two) -> one.getTitle().compareTo(two.getTitle())
        );
        System.out.println("Songs sorted by title: " + songs);

//        ArtistComparator artistComparator = new ArtistComparator();
        songs.sort(
                (one, two) -> one.getArtist().compareTo(two.getArtist())
        );
        System.out.println("Songs sorted by artist: " + songs);

//        Set<Song> songSet = new HashSet<>(songs);
        Set<Song> songSet = new TreeSet<>(songs);
        System.out.println(songSet);
    }
}
