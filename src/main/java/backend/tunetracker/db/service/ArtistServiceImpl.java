package backend.tunetracker.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.tunetracker.db.model.Artist;
import backend.tunetracker.db.repository.ArtistRepository;

@Service // // Indicates that this class is a service class, meaning it contains business logic.
public class ArtistServiceImpl implements ArtistService {
    private ArtistRepository artistRepository;

    @Autowired // This annotation is used to let Spring know that it should inject an instance of ArtistRepository into this class.
    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> fetchAllArtists() {
        return artistRepository.findAll(); // This method is used to fetch all the artists from the database. Provided by the JpaRepository interface.
    }
    
}
