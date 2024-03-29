package backend.tunetracker.db.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Emulate the Playlist table
 * 
 * @author Thomas Garcia
 * */
@Entity
@Table(name = "playlist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "playlist_name")
    private String name;

    @Column(name = "user_id")
    private UUID user_id;

    public Playlist(){} // Spring boot, it needs this to create an instance of the class via reflection. A default constructor allows Spring to create a new instance of your bean without needing any arguments. Spring Boot heavily relies on dependency injection, where beans are injected with their dependencies through constructors or setter methods.

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public UUID getUser_id() {
        return user_id;
    }
}
