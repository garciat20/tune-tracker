package backend.tunetracker.db.model;

import java.io.Serializable;
import java.util.UUID;

/*
 * This class is used to represent the composite primary key of the Follows table
 * Mapping composite keys for JPA entities is a bit more complex than mapping single keys.
 * In order to define the composite primary keys, we should follow some rules:
 * The composite primary key class must be public.
 * It must have a no-arg constructor.
 * It must define the equals() and hashCode() methods.
 * It must be Serializable.
 */

public class FollowsId implements Serializable{
    private UUID follower_id;
    private UUID followee_id;

    public FollowsId(){}

    public FollowsId(UUID follower_id, UUID followee_id){
        this.follower_id = follower_id;
        this.followee_id = followee_id;
    }

    @Override
    public boolean equals(Object obj) { 
        return (this == obj) || (obj instanceof FollowsId other && follower_id.equals(other.follower_id) && followee_id.equals(other.followee_id));
    }

    /*
     * The better the hashing algorithm that we use to compute hash codes, the better the performance of hash tables.
     * Let’s have a look at a “standard” implementation that uses two prime numbers to add even more uniqueness to computed hash codes:
     */
    @Override
    public int hashCode() { 

        int hash = 7;
        hash = 31 * hash + follower_id.hashCode();
        hash = 31 * hash + followee_id.hashCode();
        return hash;
    }
}
