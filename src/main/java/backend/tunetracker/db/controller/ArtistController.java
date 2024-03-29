package backend.tunetracker.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.tunetracker.db.model.Artist;
import backend.tunetracker.db.service.ArtistServiceImpl;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {
    private ArtistServiceImpl artistImpl; // contains business logic for Artists for api to call/acess

    @Autowired // ArtistImpl is a bean (?) that implements some interface or provides functionality related to Artists via autowired annotation, Spring will automatically instantiate an instance of ArtistImpl and inject it into the class where @Autowired is used.
    public ArtistController(ArtistServiceImpl artistImpl){
        this.artistImpl = artistImpl;
    }

    @GetMapping // This annotation is used to map HTTP GET requests onto specific handler methods.
    public List<Artist> getAllArtists(){
        return artistImpl.fetchAllArtists();
    }

}

