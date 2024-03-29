package backend.tunetracker.db.service;

import backend.tunetracker.db.model.Song;
import backend.tunetracker.db.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class implemented SongService interface to unique provide business logic
 * for Songs
 *
 * @author Thomas Garcia
 * */
@Service // Indicates that this class is a service class, meaning it contains business logic.
public class SongServiceImpl implements SongService{
    private SongRepository songRepository;
    
    @Autowired // This annotation is used to let Spring know that it should inject an instance of SongRepository into this class.
    public SongServiceImpl(SongRepository songRepository){
        this.songRepository = songRepository; 
    }

    @Override
    public List<Song> fetchAllSongs() {
        return songRepository.findAll();
    }
}
