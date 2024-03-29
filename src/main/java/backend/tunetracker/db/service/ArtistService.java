package backend.tunetracker.db.service;

import java.util.List;

import backend.tunetracker.db.model.Artist;

/*
 * An interface that's made for storage/ retrieval of Artist
 * can add special methods as needed
 */
public interface ArtistService {

    List<Artist>fetchAllArtists();
} 
    
