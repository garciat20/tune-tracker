package backend.tunetracker.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.tunetracker.db.model.Follows;

/*
 * An interface that's made for storage/ retrieval of Follows
 * 
 * @Author Thomas Garcia
 */
@Repository
public interface FollowsRepository extends JpaRepository<Follows, Long>{ // JpaRepository is an interface that provides methods for performing CRUD operations on a specific entity. It's a generic interface, where T is the type of the entity to be managed and ID is the type of the entity's identifier.
    
}
