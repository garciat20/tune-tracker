package backend.tunetracker.db.entity;

import backend.tunetracker.Main;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class PlaylistSql {
    // tables
    private static final String PLAYLIST_TABLE = "playlist";
    private static final String SONGS_IN_PLAYLIST_TABLE = "songs_in_playlist";

    // columns
    private static final String PLAYLIST_ID = "id";
    private static final String PLAYLIST_NAME = "playlist_name";
    private static final String USER_ID = "user_id";
    private static final String SONG_ID = "song_id";


    /**
     * Make method to create a playlist
     * */
    public static void createUserPlaylist(String playlistName, String uuid){
        try {
            PreparedStatement ps = Main.sql.getCon().prepareStatement("INSERT INTO " +
                    PLAYLIST_TABLE + "(" +
                    PLAYLIST_NAME + ", " + USER_ID +
                    ")VALUES (?,?);");

            ps.setString(1, playlistName);
            ps.setString(2, uuid);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("cant create playlist");

        }
    }

    /**
     * TODO: METHODS FOR --> getting a playlist id from a a playlist name
     * */
    public static void getPlaylistId(String playlistName){

    }



    /**
     * Make method to add a song to a playlist
     * */
    public static void addSongToPlaylist(int playlistId, int songId){

    }

    /**
     * Make method to view playlists from a user
     *
     * */
    public static void viewSongs(){

    }



}
