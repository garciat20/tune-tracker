package backend.tunetracker.db.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * TODO: Emulate Artist_Song table
 *TODO: STUCK ON NOT GETTING EMPTY QUERIES RAHHH
 * @author Thomas Garcia
 * */
@Entity
@Table(name = "artist_songs")
public class ArtistSongs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // empty queries, no need to fetch all artist songs, how to do that loool

    // @JoinColumn(name = "artist_id", referencedColumnName = "id")
    // private Artist artist;

    // @JoinColumn(name = "song_id", referencedColumnName = "id")
    // private Song song;
  
    public ArtistSongs(){}
    

}
