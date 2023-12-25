package backend.tunetracker.model;

import java.util.UUID;

/**
 * Class represents a User
 * */
public class User {
    private final UUID uuid; // when user is created, database generates new id
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;


    public User(UUID uuid, String username, String password, String email, String firstName, String lastName){
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}

