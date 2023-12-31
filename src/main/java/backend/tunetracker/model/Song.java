package backend.tunetracker.model;

import java.sql.Date;

/**
 * Class represents a simple song
 * */
public class Song {
   private String song_name;
   private String[] artist;
   private Date release_year;

    public Song(String song_name, String[] artist, Date release_year){
        this.song_name = song_name;
        this.artist = artist;
        this.release_year = release_year;
    }

    public Date getRelease_year() {
        return release_year;
    }

    public String getSong_name() {
        return song_name;
    }

    public String[] getArtist() {
        return artist;
    }

}
