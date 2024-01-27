package backend.tunetracker.db.repository;

import backend.tunetracker.db.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * An interface that's made for storage/ retrieval of Users
 *
 * @author Thomas Garcia
 * */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
