package backend.tunetracker.db.sql;

import backend.tunetracker.Main;
import backend.tunetracker.db.helpers.PrintStatement;
import backend.tunetracker.db.model.Song;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Class to interact with database concerning Songs
 *
 * @author Thomas Garcia
 * */

public class SongSql {
    //tables
    private static final String SONGS_TABLE = "songs";
    private static final String SONGS_IN_PLAYLIST_TABLE = "songs_in_playlist";
    private static final String PLAYLIST_TABLE = "playlist";
    private static final String ARTISTS_TABLE = "artist";
    private static final String ARTIST_SONGS_TABLE = "artist_songs";
    // columns
    
    private static final String PLAYLIST_USER_ID = "user_id";
    private static final String GENERAL_ID = "id";
    private static final String SONG_IN_PLAYLIST_TABLE_SONG_ID = "song_id";
    private static final String SONG_NAME = "song_name";
    private static final String PLAYLIST_ID = "playlist_id";
    private static final String RELEASE_YEAR = "release_year";
    private static final String DURATION = "duration";
    private static final String PLAYLIST_NAME = "playlist_name";

    public static void insertIntoSongTable(String songName, Date releaseYear, Time duration){
        // SQL STATEMENT TO INSERT
    }


    /**
     * Make method to view songs from song table
     * prompt if they'd like to search via filter but that's a FUTURE IMPLEMENTATION
     * */
    public static void viewSongsFromPlaylist(int playlistId, String userUuid){
        try {
            PreparedStatement ps = Main.sql.getCon().prepareStatement("SELECT " + SONGS_TABLE +"."+ SONG_NAME +
                    " FROM " + SONGS_TABLE +
                    "\n INNER JOIN " + SONGS_IN_PLAYLIST_TABLE + " ON " + SONGS_TABLE + "." + GENERAL_ID + " = "+
                    SONGS_IN_PLAYLIST_TABLE + "." + SONG_IN_PLAYLIST_TABLE_SONG_ID  +
                    "\n INNER JOIN " + PLAYLIST_TABLE + " ON " + SONGS_IN_PLAYLIST_TABLE + "." + PLAYLIST_ID + " = " +
                    PLAYLIST_TABLE + "." + GENERAL_ID +
                    "\n WHERE " + PLAYLIST_TABLE + "." + PLAYLIST_USER_ID + " =? AND " +
                    PLAYLIST_TABLE + "." + GENERAL_ID + " =?;");

            ps.setString(1, userUuid);
            ps.setInt(2, playlistId);
            ResultSet rs = ps.executeQuery();
            List<String> songNames = new LinkedList<>();
            while (rs.next()){

                songNames.add(rs.getString(SONG_NAME));
            }

            PrintStatement.printSongsFromPlaylist(PlaylistSql.getPlaylistName(playlistId),songNames);
        } catch (SQLException e) {
            System.out.println("Error getting songs from a user's playlist");
        }
    }

    public static List<Song> randomSongGenerator(){
        List<Song> songs = new LinkedList<>();
        try {
            PreparedStatement ps = Main.sql.getCon().prepareStatement("SELECT " + SONG_NAME +
                    " FROM  " + SONGS_TABLE +
                    "\n ORDER BY RAND()" +
                    "\n LIMIT 5;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String songName = rs.getString("song_name");
                Song song = new Song(songName);
                songs.add(song);
//                System.out.println(rs.getString("song_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error generating random songs");
        }
        return songs;
//        System.out.println("5 Random Songs to choose from");
    }

    /**
     * Very simple way of getting a songId as there aren't any duplicates in database
     * */
    public static int getSongId(String songName){
        int songId = -1;
        try {
            PreparedStatement ps = Main.sql.getCon().prepareStatement("SELECT " +
                    GENERAL_ID + " FROM " + SONGS_TABLE + " WHERE " + SONG_NAME +
                    " =?;");
            ps.setString(1, songName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                songId = rs.getInt(GENERAL_ID);
            }
        }catch (SQLException e){
            System.out.println("Error getting songId");
        }
        return  songId;
    }

    /**
     * FUTURE IMPLEMENTION TO FILTER
     * */
    public static void filterSongs(String searchCriteria){

    }

}
