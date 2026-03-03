package com.example.jukebox;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        new Main().go();
    }

    public void go() {
        List<Song> songs = MockSongs.getSongs();
        IO.println("Songs List: " + songs);

        Collections.sort(songs);  //senza l'implementazione di comparable non avrei potuto fare il sort e quindi l'ordinamento
        IO.println("Songs List sorted by title: " + songs);

        ArtistComparator artistComparator = new ArtistComparator();
        songs.sort(artistComparator);
        IO.println("Songs List with comparator artist: \n" + songs);

        TitleComparator titleComparator = new TitleComparator();
        songs.sort(titleComparator);
        IO.println("Songs List with comparator title: \n" + songs);

        songs.sort((Song one, Song two) -> one.getTitle().compareTo(two.getTitle())); //cosi evito di creare la classe TitleComparator
        IO.println("Songs List sorted by title: \n" + songs);

        songs.sort((Song one, Song two) -> one.getArtist().compareTo(two.getArtist())); //cosi evito di creare la classe ArtistComparator
        IO.println("Songs List sorted by Artist: \n" + songs);


        IO.println("\n");
        Set<Song> songSet = new HashSet<>(songs);
        IO.println(songSet);
        Set<Song> songTreeSet = new TreeSet<>(songs);
        IO.println(songTreeSet);
    }
}
