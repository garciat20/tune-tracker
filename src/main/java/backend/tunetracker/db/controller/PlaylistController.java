package backend.tunetracker.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.tunetracker.db.model.Playlist;
import backend.tunetracker.db.service.PlaylistServiceImpl;

/*
 * This class is used to handle HTTP requests for the Playlist table in the database.
 * TODO: Somehow get the user's actual name in the request via joins 
 */

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {
    private PlaylistServiceImpl playlistService;

    @Autowired // This annotation is used to let Spring know that it should inject an instance of PlaylistServiceImpl into this class.
    public PlaylistController(PlaylistServiceImpl playlistService){
        this.playlistService = playlistService;
    }

    @GetMapping // This annotation is used to map HTTP GET requests onto specific handler methods.
    public List<Playlist> getAllPlaylists(){
        return playlistService.fetchAllPlaylists(); // This method is used to fetch all the playlists from the database.
    }

}
