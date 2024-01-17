package backend.tunetracker.db.entity;

import backend.tunetracker.Main;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
public class SongSql {
    //tables
    private static final String SONGS_TABLE = "songs";
    private static final String SONGS_IN_PLAYLIST_TABLE = "songs_in_playlist";
    private static final String PLAYLIST_TABLE = "playlist";
    private static final String ARTISTS_TABLE = "artist";
    private static final String ARTIST_SONGS_TABLE = "artist_songs";
    // columns
    /**
     * TODO: FINISH COLUMN FINAL VARIABLES
     * */
    private static final String GENERAL_ID = "id";
    private static final String SONG_IN_PLAYLIST_TABLE_SONG_ID = "song_id";
    private static final String SONG_NAME = "song_name";
    private static final String PLAYLIST_ID = "playlist_id";
    private static final String RELEASE_YEAR = "release_year";
    private static final String DURATION = "duration";
    private static final String PLAYLIST_NAME = "playlist_name";

    public static void insertIntoTable(String songName, Date releaseYear, Time duration){
        // SQL STATEMENT TO INSERT
    }
    /**
     * Make method to view songs from song table
     * prompt if they'd like to search via filter but that's a FUTURE IMPLEMENTATION
     * TODO: CHECK IF WORKS
     * DOUBLE CHECK GENERAL ID
     * */
    public static void viewSongsFromPlaylist(int playlistId, String userUuid){
        try {
            PreparedStatement ps = Main.sql.getCon().prepareStatement("SELECT " + SONGS_TABLE +"."+ SONG_NAME +
                    " FROM " + SONGS_TABLE +
                    "\n INNER JOIN " + SONGS_IN_PLAYLIST_TABLE + " ON " + SONGS_TABLE + "." + SONG_IN_PLAYLIST_TABLE_SONG_ID + " = "+
                    SONGS_IN_PLAYLIST_TABLE + "." + SONG_IN_PLAYLIST_TABLE_SONG_ID  +
                    "\n INNER JOIN " + PLAYLIST_TABLE + " ON " + SONGS_IN_PLAYLIST_TABLE + "." + PLAYLIST_ID + " = " +
                    PLAYLIST_TABLE + "." + GENERAL_ID);
            // FINISH WHERE CLAUSE`
        } catch (SQLException e) {
            System.out.println("Error getting songs from a user's playlist");
        }
    }


    /**
     * FUTURE IMPLEMENTION TO FILTER
     * */
    public static void filterSongs(String searchCriteria){

    }

}
