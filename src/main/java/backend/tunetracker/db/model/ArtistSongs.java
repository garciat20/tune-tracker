package backend.tunetracker.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *  Emulate Artist_Song table
 *
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
  
    @Column(name = "artist_id")
    private int artist_id;

    @Column(name = "song_id")
    private int song_id;

    public ArtistSongs(){}
    
    public int getArtist_id() {
        return artist_id;
    }
    public int getSong_id() {
        return song_id;
    }
}
