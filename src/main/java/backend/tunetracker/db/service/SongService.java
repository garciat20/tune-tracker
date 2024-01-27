package backend.tunetracker.db.service;

import backend.tunetracker.db.model.Song;

import java.util.List;

public interface SongService {
    //read
    List<Song> fetchAllSongs();
}
