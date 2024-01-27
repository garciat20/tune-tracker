package backend.tunetracker.db.controller;

import org.springframework.web.bind.annotation.RequestParam;

import backend.tunetracker.db.model.User;
import backend.tunetracker.db.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Class represents a way to access the business logic (Service layer)/ exposing functionality
 * for Users
 * @author Thomas Garcia
 * */

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService; //also try just with the service

   @GetMapping
    public List<User> getAllUsers(){
        return userService.fetchAllUsers();
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name){
        return String.format("Hello %s!", name);
    }
}
