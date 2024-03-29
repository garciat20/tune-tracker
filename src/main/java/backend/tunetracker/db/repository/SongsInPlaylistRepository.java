package backend.tunetracker.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.tunetracker.db.model.SongsInPlaylist;

/*
 * An interface that's made for storage/ retrieval of SongsInPlaylist
 * 
 * @author Thomas Garcia
 
 */

@Repository
public interface SongsInPlaylistRepository extends JpaRepository<SongsInPlaylist, Long>{ // JpaRepository is an interface that provides methods for performing CRUD operations on a specific entity. It's a generic interface, where T is the type of the entity to be managed and ID is the type of the entity's identifier.
    
}
