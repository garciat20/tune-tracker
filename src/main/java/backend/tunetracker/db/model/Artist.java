package backend.tunetracker.db.model;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


/**
 * Class that represents a simple Artist
 *
 * @author Thomas Garcia
 * */
@Entity
@Table(name= "artist")
public class Artist {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // this annotation means the ID is automatically generated by the database
    private Long id;
    @Column(name = "name") // this annotation specifies the name of the column in the database, it doesn't match the variable name 'aritst' hence we specify it here
    private String artist;

    @Transient
    @JsonIgnore
    private List<String> songs;

    public Artist(String artist){
        this.artist = artist;
        this.songs = new LinkedList<>();
    }

    // public int getId() { maybe add back the field for id? its auto generated
    //     return id;
    // }

    public List<String> getSongs() { 
        return songs;
    }

    public String getArtist() {
        return artist;
    }

    public void addSong(String song) {
        this.songs.add(song);
    }
}
