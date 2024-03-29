package backend.tunetracker.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.tunetracker.db.model.ArtistSongs;

/**
 * An interface that's made for storage/ retrieval of ArtistSongs
 * 
 * @Author Thomas Garcia
 */
@Repository
public interface ArtistSongsRepository extends JpaRepository<ArtistSongs, Long>{
    
}
