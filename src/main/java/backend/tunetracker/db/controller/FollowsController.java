package backend.tunetracker.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.tunetracker.db.model.Follows;
import backend.tunetracker.db.service.FollowsServiceImpl;

/*
 * This class is used to handle HTTP requests for the Follows table in the database.
 * TODO: Somehow get a user's followers and following in the request via joins (maybe call my vanilla SQL query in the service layer?) 
 */

@RestController // This annotation is used to create RESTful web services using Spring MVC. It's a specialization of @Component, which means it's a Spring-managed bean.
@RequestMapping("/api/follows") // This annotation is used to map web requests onto specific handler classes and/or handler methods.
public class FollowsController {
    private FollowsServiceImpl followsImpl;

    @Autowired // This annotation is used to let Spring know that it should inject an instance of FollowsServiceImpl into this class.
    public FollowsController(FollowsServiceImpl followsImpl){
        this.followsImpl = followsImpl;
    }

    @GetMapping // This annotation is used to map HTTP GET requests onto specific handler methods.
    public List<Follows> getAllFollows(){
        return followsImpl.fetchAllFollows();
    }
}
