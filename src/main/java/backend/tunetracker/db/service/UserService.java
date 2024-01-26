package backend.tunetracker.db.service;

import backend.tunetracker.db.model.User;

import java.util.List;

public interface UserService {

    //read
    List<User> fetchAllUsers();
}
