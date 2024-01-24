package backend.tunetracker.db.entity;

import backend.tunetracker.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


/**
 * Class to interact with database concerning Playlists
 *
 * @author Thomas Garcia
 * */
public class PlaylistSql {
    // tables
    private static final String PLAYLIST_TABLE = "playlist";
    private static final String SONGS_IN_PLAYLIST_TABLE = "songs_in_playlist";

    // columns
    private static final String PLAYLIST_ID = "id";
    private static final String SONGS_IN_PLAYLIST_PLAYLIST_ID = "playlist_id";
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
     * Gets playlist id
     * */
    public static int getPlaylistId(String playlistName, String userUuid){
        int playlistId = -1;
        try {
            PreparedStatement ps = Main.sql.getCon().prepareStatement("SELECT " + PLAYLIST_ID +
                    " FROM " + PLAYLIST_TABLE + " WHERE " + PLAYLIST_NAME + " =? AND " +
                    USER_ID + " =?;");
            ps.setString(1, playlistName);
            ps.setString(2, userUuid);

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                playlistId = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("error getting playlist id");
        }
        return  playlistId;
    }

    /**
     * Gets list of playlist names of a user
     * */
    public static List<String> getPlaylistNames(String userUuid){
        List<String> playlistNames = new LinkedList<>();
        try {
            PreparedStatement ps = Main.sql.getCon().prepareStatement("SELECT " + PLAYLIST_NAME +
                    " FROM " + PLAYLIST_TABLE + " WHERE " + USER_ID + " =?;");

            ps.setString(1, userUuid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                playlistNames.add(rs.getString((PLAYLIST_NAME)));
//                System.out.println( rs.getString("playlist_name"));
            }
        }catch(SQLException e){
            System.out.println("Issue with getting playlist names for a specific user");
        }
        return playlistNames;

    }



    /**
     * Make method to add a song to a playlist
     * */
    public static void addSongToPlaylist(int playlistId, int songId){
        try {
            PreparedStatement ps = Main.sql.getCon().prepareStatement("INSERT INTO " +
                    SONGS_IN_PLAYLIST_TABLE + "(" +
                    SONGS_IN_PLAYLIST_PLAYLIST_ID + ", " + SONG_ID + ") " +
                    "VALUES (?, ?);");
            ps.setInt(1, playlistId);
            ps.setInt(2, songId);
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error adding songs to playlist");

        }

    }

    /**
     *
     * */
    public static String getPlaylistName(int playlistId){
        String playlistName = "";
        try {
            PreparedStatement ps = Main.sql.getCon().prepareStatement("SELECT " + PLAYLIST_NAME +
                    " FROM " + PLAYLIST_TABLE +
                    " WHERE " + PLAYLIST_TABLE + "." + PLAYLIST_ID + " =?;");
            ps.setInt(1, playlistId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                playlistName = rs.getString(1);
            }
        }catch(SQLException e){
            System.out.println("Error getting playlist name");
        }
        return playlistName;
    }
    //    /**
//     * maybe not useful
//     * */
//    public static void getPlaylistsViaUuidAndPlaylistName(String playlistName, String userUuid){
//        try {
//            PreparedStatement ps = Main.sql.getCon().prepareStatement("SELECT" +
//                    PLAYLIST_NAME + " FROM " + PLAYLIST_TABLE + " WHERE " + USER_ID
//            + " =? AND " + PLAYLIST_NAME +" =?;");
//
//            ps.setString(1, userUuid);
//            ps.setString(2, playlistName);
//
//            ResultSet rs = ps.executeQuery();
//            // print playlists for user
//            while (rs.next()){
//                System.out.println(rs.getString("playlist_name"));
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Playlist name error");
//        }
//

//    }

}
