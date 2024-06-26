package backend.tunetracker.db.service;

import backend.tunetracker.db.model.User;
import backend.tunetracker.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class implemented UserService interface to unique provide business logic for Users
 *
 * @author Thomas Garcia
 * */
@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }
}
