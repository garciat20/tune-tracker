package backend.tunetracker.db.service;

import java.util.List;

import backend.tunetracker.db.model.Follows;

/*  
 * An interface that's made for storage/ retrieval of Follows
 * Can add special meethods as needed
 * 
 * @Author Thomas Garcia
 */

public interface FollowsService {

    List<Follows> fetchAllFollows();
}
