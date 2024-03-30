package backend.tunetracker.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.tunetracker.db.model.SongsInPlaylist;
import backend.tunetracker.db.service.SongsInPlaylistServiceImpl;

/*
 * This class is used to handle HTTP requests for the SongsInPlaylist table in the database.
 * TODO: Somehow get the actual playlist name, and the song name
 
 */

@RestController
@RequestMapping("/api/songs-in-playlist")
public class SongsInPlaylistController {
    private SongsInPlaylistServiceImpl songsInPlaylistImpl;

    @Autowired // This annotation is used to let Spring know that it should inject an instance of SongsInPlaylistServiceImpl into this class.
    public SongsInPlaylistController(SongsInPlaylistServiceImpl songsInPlaylistImpl){
        this.songsInPlaylistImpl = songsInPlaylistImpl;
    }

    @GetMapping
    public List<SongsInPlaylist> getAllSongsInPlaylist(){
        return songsInPlaylistImpl.fetchAllSongsInPlaylist();
    }
}
