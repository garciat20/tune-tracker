package backend.tunetracker.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.tunetracker.db.model.SongsInPlaylist;
import backend.tunetracker.db.repository.SongsInPlaylistRepository;

/*
 * Class implemented SongsInPlaylistService interface to unique provide business logic for SongsInPlaylist
 */

@Service  // Indicates that this class is a service class, meaning it contains business logic.
public class SongsInPlaylistServiceImpl implements SongsInPlaylistService{

    private SongsInPlaylistRepository songsInPlaylistRepository;

    @Autowired // This annotation is used to let Spring know that it should inject an instance of SongsInPlaylistRepository into this class.
    public SongsInPlaylistServiceImpl(SongsInPlaylistRepository songsInPlaylistRepository){
        this.songsInPlaylistRepository = songsInPlaylistRepository;
    }

    @Override
    public List<SongsInPlaylist> fetchAllSongsInPlaylist() {
        return songsInPlaylistRepository.findAll();
    }
    
}
