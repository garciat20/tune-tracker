package backend.tunetracker.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.tunetracker.db.model.Follows;
import backend.tunetracker.db.repository.FollowsRepository;

/*
 * Class implemented FollowsService interface to unique provide business logic for Follows
 * 
 * @Author Thomas Garcia
 */

@Service // Indicates that this class is a service class, meaning it contains business logic.
public class FollowsServiceImpl implements FollowsService{

    private FollowsRepository followsRepository;

    @Autowired // This annotation is used to let Spring know that it should inject an instance of FollowsRepository into this class.
    public FollowsServiceImpl(FollowsRepository followsRepository){
        this.followsRepository = followsRepository;
    }

    @Override
    public List<Follows> fetchAllFollows() {
        return followsRepository.findAll(); // This method is used to fetch all the follows from the database. Provided by the JpaRepository interface.
    }
    
}
