package backend.tunetracker.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

/**
 *  Emulate Songs_In_Playlist table
 *  NOTE; only have foreign keys here so we can make a composite primary key with the foreign keys
 * @author Thomas Garcia
 * */

@Entity
@Table(name = "songs_in_playlist")
@IdClass(SongsInPlaylistId.class)
public class SongsInPlaylist {

    // this is a foriegn key
    @Id
    @Column(name = "playlist_id")
    private int playlist_id;

    // this is a foriegn key
    @Id
    @Column(name = "song_id")
    private int song_id;

    public SongsInPlaylist(){}

    public int getPlaylist_id() {
        return playlist_id;
    }
    public int getSong_id() {
        return song_id;
    }
}
