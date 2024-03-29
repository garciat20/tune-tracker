package backend.tunetracker.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.tunetracker.db.model.ArtistSongs;
import backend.tunetracker.db.repository.ArtistSongsRepository;

/*
 * Class implemented ArtistSongs interface to unique provide business logic
 * for Songs
 */

@Service // Indicates that this class is a service class, meaning it contains business logic.
public class ArtistSongsServiceImpl implements ArtistSongsService{
    private ArtistSongsRepository artistSongsRepository;

    @Autowired // This annotation is used to let Spring know that it should inject an instance of ArtistSongsRepository into this class.
    public ArtistSongsServiceImpl(ArtistSongsRepository artistSongsRepository){
        this.artistSongsRepository = artistSongsRepository;
    }

    @Override
    public List<ArtistSongs> fetchAllArtistSongs() {
        return artistSongsRepository.findAll();
    }
    
}
