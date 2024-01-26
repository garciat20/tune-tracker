package backend.tunetracker.db.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import backend.tunetracker.db.model.User;
import backend.tunetracker.db.repository.UserRepository;
import backend.tunetracker.db.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

   @GetMapping
    public List<User> getAllUsers(){
        return userService.fetchAllUsers();
    }

//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers(){
//        List<User> users  =  userService.fetchAllUsers();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name){
        return String.format("Hello %s!", name);
    }
}
