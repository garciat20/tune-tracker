package backend.tunetracker.model;

import java.sql.Date;
import java.util.UUID;

/**
 * Class represents a User
 * */
public class User {
    private final UUID uuid;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Date creationDate;
    private Date lastAccessDate;


    public User(UUID uuid, String username, String email, String firstName, String lastName, Date creationDate, Date lastAccessDate){
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creationDate = creationDate;
        this.lastAccessDate = lastAccessDate;
    }

    public UUID getUuid() {
        return uuid;
    }
    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastAccessDate() {
        return lastAccessDate;
    }
}

