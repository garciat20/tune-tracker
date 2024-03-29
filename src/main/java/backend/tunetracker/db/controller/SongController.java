package backend.tunetracker.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.tunetracker.db.model.Song;
import backend.tunetracker.db.service.SongServiceImpl;

/**
 * Class represents a way to access the business logic (Service layer)/ exposing functionality
 * for Songs
 * NOTES: Controller: Similar to the Controller in MVC, it handles user input and delegates business logic to the Service layer.
 * @author Thomas Garcia
 * */
@RestController // Indicates that this class is a REST controller, meaning it handles HTTP requests and returns responses as RESTful services.
@RequestMapping("/api/songs") // this annotation specifies that all HTTP requests starting with /api/songs will be handled by this class
public class SongController {
    private SongServiceImpl songService;

    @Autowired// This annotation is used to let Spring know that it should inject an instance of SongServiceImpl into this class.
    public SongController(SongServiceImpl songService){
        this.songService = songService;
    }

    @GetMapping
    public List<Song> getAllSongs(){
        return songService.fetchAllSongs();
    }
    @GetMapping("/test")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name){
        return String.format("song name is: %s!", name);
    }
}
