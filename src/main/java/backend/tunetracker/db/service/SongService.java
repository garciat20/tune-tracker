package backend.tunetracker.db.service;

import backend.tunetracker.db.model.Song;

import java.util.List;

/**
 * Interface that provides a contract for the SongService - used for business logic
 * can add special methods as needed
 * 
 * @Author Thomas Garcia
 */
public interface SongService {
    //read
    List<Song> fetchAllSongs();
}
