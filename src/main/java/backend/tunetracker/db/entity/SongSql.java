package backend.tunetracker.db.entity;

import backend.tunetracker.Main;

import java.sql.*;

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
                    PLAYLIST_TABLE + "." + GENERAL_ID +
                    "\n WHERE " + PLAYLIST_TABLE + "." + PLAYLIST_USER_ID + " =? AND " +
                    PLAYLIST_TABLE + "." + PLAYLIST_ID + " =?;");

            ps.setString(1, userUuid);
            ps.setInt(2, playlistId);
            ResultSet rs = ps.executeQuery();
            System.out.println("======= DEBUGGING: BELOW ARE SONG NAMES DEBUGGING ========");
            while (rs.next()){
                System.out.println(rs.getString("song_name"));
            }
            // FINISH WHERE CLAUSE`
        } catch (SQLException e) {
            System.out.println("Error getting songs from a user's playlist");
        }
    }

    public static void randomSongGenerator(){
        try {
            PreparedStatement ps = Main.sql.getCon().prepareStatement("SELECT " + SONG_NAME +
                    " FROM  " + SONGS_TABLE +
                    "\n ORDER BY RAND()" +
                    "\n LIMIT 3;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("song_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error generating random songs");
        }
        System.out.println("5 Random Songs to choose from");
    }


    /**
     * FUTURE IMPLEMENTION TO FILTER
     * */
    public static void filterSongs(String searchCriteria){

    }

}
