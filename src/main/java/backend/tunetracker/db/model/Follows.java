package backend.tunetracker.db.model;


import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

/**
 * Emulate the follows table
 * NOTE; WE HAVE A COMPOSITE PRIMARY KEY HERE
 * Also, @IdClass can be quite useful in places where we are using a composite key class that we can’t modify.
 * @author Thomas Garcia
 * */

@Entity
@Table(name = "follows")
@IdClass(FollowsId.class)
public class Follows{
    @Id
    @Column(name = "follower_id")
    private UUID follower_id;
    
    @Id
    @Column(name = "followee_id")
    private UUID followee_id;

    public Follows(){}

    public UUID getFollowee_id() {
        return followee_id;
    }

    public UUID getFollower_id() {
        return follower_id;
    }
}
