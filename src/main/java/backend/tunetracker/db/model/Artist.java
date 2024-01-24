package backend.tunetracker.db.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Class that represents a simple Artist
 *
 * @author Thomas Garcia
 * */
public class Artist {
    private LinkedList<String> songs = new LinkedList();
    private String artist;
    private int id;
    public Artist(int id, String artist){
        this.artist = artist;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public LinkedList<String> getSongs() {
        return songs;
    }

    public String getArtist() {
        return artist;
    }

    public void addSong(String song) {
        this.songs.add(song);
    }
}
