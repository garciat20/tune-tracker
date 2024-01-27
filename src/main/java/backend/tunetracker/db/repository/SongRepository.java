package backend.tunetracker.db.repository;

import backend.tunetracker.db.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * An interface that's made for storage/ retrieval of Songs
 *
 * @author Thomas Garcia
 * */
@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
}
