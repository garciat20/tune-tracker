package backend.tunetracker.db.repository;

import backend.tunetracker.db.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * An interface that's made for storage/ retrieval of Songs
 *
 * @author Thomas Garcia
 * */
@Repository // Indicates that this class is a repository class, meaning it's used to store and retrieve data. -used for database operations.
public interface SongRepository extends JpaRepository<Song, Long> { // JpaRepository is an interface that provides methods for performing CRUD operations on a specific entity. It's a generic interface, where T is the type of the entity to be managed and ID is the type of the entity's identifier. 
}
