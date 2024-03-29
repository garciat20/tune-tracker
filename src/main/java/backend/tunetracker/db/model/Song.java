package backend.tunetracker.db.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Class represents a simple song
 *
 * @author Thomas Garcia
 * */
@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Transient
    @JsonIgnore
    private List<Artist> artist;
    
    @Column(name = "song_name")
    private String songName;
    // @Transient // this annotation tells the JPA to ignore this field when creating the table (doesn't even exist in this table)
    // @JsonIgnore // this annotation tells the Jackson library to ignore this field when serializing the object to JSON
    // private List<Artist> artist;
    @Column(name = "release_year")
    private Date releaseYear;
    @Column(name = "duration")
    private Time duration;

    public Song(){}

    public Song(String songName, List<Artist> artist, Date releaseYear){
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

    // public Long getId() {
    //     return id;
    // }

    public Time getDuration() {
        return duration;
    }

    public String getSongName() {
        return songName;
    }

    public List<Artist> getArtist() {
        return artist;
    }

}
