package backend.tunetracker.model;

import java.sql.Date;

/**
 * Class represents a simple song
 *
 * @author Thomas Garcia
 * */
public class Song {
   private String songName;
   private String[] artist;
   private Date releaseYear;

    public Song(String songName, String[] artist, Date releaseYear){
        this.songName = songName;
        this.artist = artist;
        this.releaseYear = releaseYear;
    }

    public Song(String songname){
        this.songName = songname;
    }

    public Date getReleaseYear() {
        return releaseYear;
    }

    public String getSongName() {
        return songName;
    }

    public String[] getArtist() {
        return artist;
    }

}
