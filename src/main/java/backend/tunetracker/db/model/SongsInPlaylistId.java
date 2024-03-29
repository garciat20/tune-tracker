package backend.tunetracker.db.model;

import java.io.Serializable;

/*
 * This class is used to create a composite primary key for the SongsInPlaylist class
 * Mapping composite keys for JPA entities is a bit more complex than mapping single keys.
 * In order to define the composite primary keys, we should follow some rules:
 * The composite primary key class must be public.
 * It must have a no-arg constructor.
 * It must define the equals() and hashCode() methods.
 * It must be Serializable.
 
 */

public class SongsInPlaylistId implements Serializable{
    private int playlist_id;
    private int song_id;

    public SongsInPlaylistId(){}

    @Override
    public boolean equals(Object obj) {
        return (this == obj) || (obj instanceof SongsInPlaylistId other && playlist_id == other.playlist_id && song_id == other.song_id);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + playlist_id;
        hash = 31 * hash + song_id;
        return hash;
    }
}
