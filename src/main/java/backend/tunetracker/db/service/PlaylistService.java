package backend.tunetracker.db.service;

import java.util.List;

import backend.tunetracker.db.model.Playlist;

/*
 * An interface that's made for storage/ retrieval of Playlist
 */

public interface PlaylistService {
    List<Playlist> fetchAllPlaylists();
}
