package backend.tunetracker.db.service;

import java.util.List;

import backend.tunetracker.db.model.SongsInPlaylist;

public interface SongsInPlaylistService {
    List<SongsInPlaylist> fetchAllSongsInPlaylist();
}
