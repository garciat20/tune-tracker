package backend.tunetracker.db.controller;

import backend.tunetracker.db.model.Song;
import backend.tunetracker.db.service.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Class represents a way to access the business logic (Service layer)/ exposing functionality
 * for Songs
 * @author Thomas Garcia
 * */
@RestController
@RequestMapping("/api/songs")
public class SongController {
    @Autowired
    private SongServiceImpl songService;

    @GetMapping
    public List<Song> getAllSongs(){
        return songService.fetchAllSongs();
    }
    @GetMapping("/test")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name){
        return String.format("song name is: %s!", name);
    }
}
