package backend.tunetracker.db.service;

import java.util.List;

import backend.tunetracker.db.model.ArtistSongs;

public interface ArtistSongsService {
    //read
    List<ArtistSongs> fetchAllArtistSongs();
}
