package backend.tunetracker.db.service;

import backend.tunetracker.db.model.Song;
import backend.tunetracker.db.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService{
    @Autowired
    private SongRepository songRepository;


    @Override
    public List<Song> fetchAllSongs() {
        return songRepository.findAll();
    }
}
