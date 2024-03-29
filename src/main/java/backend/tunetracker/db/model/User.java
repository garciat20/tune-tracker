package backend.tunetracker.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;
import java.util.UUID;

/**
 * Class represents a User
 *
 * @author Thomas Garcia
 * */
@Entity
@Table(name = "user")
public class User {
    @Id
    private UUID uuid;
    private String username;
    private String email;
    @Column(name = "first_name") 
    private String firstName;
    @Column(name = "last_name") 
    private String lastName;
    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "last_access_date")
    private Date lastAccessDate;
    private String password;

    public User(){}
    public User(UUID uuid, String username, String email, String firstName, String lastName, Date creationDate, Date lastAccessDate){
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creationDate = creationDate;
        this.lastAccessDate = lastAccessDate;
    }
    public User(UUID uuid, String username, String email, String password, String firstName, String lastName, Date creationDate, Date lastAccessDate){
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.password = password;
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

    public String getPassword() { return password; }
}

