package backend.tunetracker.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.tunetracker.db.model.Playlist;
import backend.tunetracker.db.repository.PlaylistRepository;

@Service // Indicates that this class is a service class, meaning it contains business logic.
public class PlaylistServiceImpl implements PlaylistService {
    private PlaylistRepository playlistRepository;

    @Autowired // This annotation is used to let Spring know that it should inject an instance of PlaylistRepository into this class.
    public PlaylistServiceImpl(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public List<Playlist> fetchAllPlaylists() {
        return playlistRepository.findAll(); // This method is used to fetch all the playlists from the database. Provided by the JpaRepository interface.
    }

    
}
