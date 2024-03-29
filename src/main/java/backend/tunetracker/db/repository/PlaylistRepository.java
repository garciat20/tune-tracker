package backend.tunetracker.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.tunetracker.db.model.Playlist;

@Repository // Indicates that this class is a repository class, meaning it's used to store and retrieve data from the database.
public interface PlaylistRepository extends JpaRepository<Playlist, Long>{ // JpaRepository is an interface that provides methods for performing CRUD operations on a specific entity. It's a generic interface, where T is the type of the entity to be managed and ID is the type of the entity's identifier.
    
}
