package backend.tunetracker.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.tunetracker.db.model.ArtistSongs;
import backend.tunetracker.db.service.ArtistSongsImpl;

/*
 * Class represents a way to access the business logic (Service layer)/ exposing functionality
 * TODO: Somehow get the actual playlist name, and the artist name
 */

@RestController
@RequestMapping("/api/artist-songs")
public class ArtistSongsController {
    private ArtistSongsImpl artistSongsImpl;

    @Autowired // ArtistSongsImpl is a bean (?) that implements some interface or provides functionality related to ArtistSongs via autowired annotation, Spring will automatically instantiate an instance of ArtistSongsImpl and inject it into the class where @Autowired is used.
    public ArtistSongsController(ArtistSongsImpl artistSongsImpl){
        this.artistSongsImpl = artistSongsImpl;
    }

    @GetMapping // This annotation is used to map HTTP GET requests onto specific handler methods.
    public List<ArtistSongs> getAllArtistSongs(){
        return artistSongsImpl.fetchAllArtistSongs();
    }

}
