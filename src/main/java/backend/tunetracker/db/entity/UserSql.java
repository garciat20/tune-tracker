package backend.tunetracker.db.entity;

import backend.tunetracker.Main;
import backend.tunetracker.db.helpers.PrintStatement;
import backend.tunetracker.model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Class to interact with database concerning Users
 *
 * @author Thomas Garcia
 * */

public class UserSql {
    private static final String BLUE  = "\u001B[34m";
    private static final String RESET = "\u001B[0m";
    // Table(s)
    private static final String USER_TABLE = "user";
    private static final String FOLLOWS_TABLE = "follows";
    // Column(s)
    private static final String USER_UUID = "uuid";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String LAST_ACCESS_DATE = "last_access_date";
    private static final String CREATION_DATE = "creation_date";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String FOLLOWER_ID = "follower_id";
    private static final String FOLLOWEE_ID = "followee_id";

    /**
     * Insert a user into the database
     * */
    public static void insertUser(User user, String password) throws SQLException{
        PreparedStatement ps = Main.sql.getCon().prepareStatement("INSERT INTO " +
                USER_TABLE + "("+
                USER_UUID+ ", " + USERNAME + ", " + PASSWORD + ", " +
                EMAIL + ", " + LAST_NAME + ", " + FIRST_NAME + ", " +
                CREATION_DATE + ", " + LAST_ACCESS_DATE +
                ") VALUES (?,?,?,?,?,?,?,?)");


        ps.setString(1, user.getUuid().toString());
        ps.setString(2, user.getUsername());
        ps.setString(3, DigestUtils.sha256Hex(password));
        ps.setString(4, user.getEmail());
        ps.setString(5, user.getLastName());
        ps.setString(6, user.getFirstName());
        ps.setDate(7, user.getCreationDate());
        ps.setDate(8,user.getLastAccessDate());
        ps.execute();
    }

    /**
     * Gets followers
     * */
    public static List<String> getFollowers(String username)throws SQLException{
        List<String> followers = new ArrayList<>();

        PreparedStatement ps = Main.sql.getCon().prepareStatement("SELECT "+ FOLLOWER_ID + " FROM "
        + FOLLOWS_TABLE +" WHERE followee_id =?;");

        String uuidFromUsername = getUUID(username); // get uuid

        ps.setString(1, uuidFromUsername); // set it from uuid
        ResultSet rs = ps.executeQuery(); // results from sql table

//        System.out.println("Followers of " + username + ": "); // traverse thru sql
        while (rs.next()){
            String uuidColumn = rs.getString(FOLLOWER_ID);
            String follower = getUsername(uuidColumn);
            System.out.println(follower);
        }
        return followers;

    }

    /**
     * Views profile concerning followers, followees, and playlists
     * */
    public static void viewProfile(String username) throws SQLException {
        System.out.println(BLUE + "Displaying " + username + "'s profile" + RESET);
        int followerCount = getFollowerCount(username);
        int followeeCount = getFolloweeCount(username);
        System.out.println("Number of followers: " + String.valueOf(followerCount));
        System.out.println("Number of people "+ username+ " is following: " + String.valueOf(followeeCount));

        String userUuid = UserSql.getUUID(username);
        List<String> playlistNames = PlaylistSql.getPlaylistNames(userUuid);

//        System.out.println(username + " playlist's are:" );
        PrintStatement.printPlaylistHeaderFooter(playlistNames, username);

//        System.out.println("Top 5 Recommended songs: (in progress)");


    }

    public static String getUUID(String username) throws SQLException {
        PreparedStatement ps = Main.sql.getCon().prepareStatement("SELECT " + USER_UUID + " FROM " + USER_TABLE +
                " WHERE "+ USERNAME + " =?;");
        ps.setString(1,username);
        ResultSet rs = ps.executeQuery();


        if (rs.next()){
            return rs.getString(USER_UUID);
        }
        return null;
    }

    public static String getUsername(String uuid)throws SQLException{
        PreparedStatement ps = Main.sql.getCon().prepareStatement(
                "SELECT " + USERNAME + " FROM " + USER_TABLE + " WHERE " + USER_UUID +" =?;"
        );
        ps.setString(1, uuid);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            String username = rs.getString(USERNAME);
            return username;
        }
        return null;
    }

    /**
     * Gets a user based on email/ password
     * */
    public static User selectByEmailPassword(String email, String password){
        try {
            PreparedStatement ps = Main.sql.getCon().prepareStatement("SELECT * FROM " +
                    USER_TABLE
                    + " WHERE " + EMAIL + "=?" + " AND " + PASSWORD + " =?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
//             now get all attributes of a user to then create said user and return it (if it exists)
                String strUuid = rs.getString(USER_UUID);
                UUID uuid = UUID.fromString(strUuid);
                String username = rs.getString(USERNAME);
                String first = rs.getString(FIRST_NAME);
                String last = rs.getString(LAST_NAME);
                Date creationDate = rs.getDate(CREATION_DATE);
                Date lastAccessDate = rs.getDate(LAST_ACCESS_DATE);

                return new User(uuid, username, email, first, last, creationDate, lastAccessDate);
            }
        } catch (SQLException e) {
            System.out.println("Connection error");
        }
        return null;
    }

    public static void updateLastAccessTime(UUID uuid)throws SQLException {
        PreparedStatement ps = Main.sql.getCon().prepareStatement("UPDATE "
        + USER_TABLE
        + " SET " + LAST_ACCESS_DATE + "=? WHERE "+ USER_UUID +" =?");
        LocalDate localDate = LocalDate.now();
        Date lastAccessTime = Date.valueOf(localDate);
        ps.setDate(1, lastAccessTime);
        ps.setString(2, uuid.toString());

    }

    /**
     * Get every uuid from users table
     * */
    public static List<UUID> getAllUuid() throws SQLException {
        PreparedStatement ps = Main.sql.getCon().prepareStatement("SELECT "+
                USER_UUID + " FROM "+ USER_TABLE +";");
        ResultSet rs = ps.executeQuery();
        ArrayList<UUID> uuidList = new ArrayList<>();

        while (rs.next()){
            String uuidStr = rs.getString("uuid");
            uuidList.add(UUID.fromString(uuidStr));
        }
        return uuidList;
    }


    /**
     * follows person
     * */
    public static void followPerson(UUID followerID, UUID followeeID){
        String followerStringId = followerID.toString();
        String followeeStringId = followeeID.toString();

        try {
            PreparedStatement ps = Main.sql.getCon().prepareStatement("INSERT INTO " +
                    FOLLOWS_TABLE + " (" +
                    FOLLOWER_ID + "," + FOLLOWEE_ID +
                    ") VALUES(" +
                    "?,?);"
            );
            ps.setString(1, followerStringId);
            ps.setString(2, followeeStringId);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Problem following person");
        }
    }

    /**
     * Unfollows person
     * */
    public static void unfollowPerson(UUID followerID, UUID followeeID){
        // make follower unfollow followee
        try {
            PreparedStatement ps = Main.sql.getCon().prepareStatement("DELETE FROM " +
                    FOLLOWS_TABLE + " WHERE " + FOLLOWER_ID + " =? AND " + FOLLOWEE_ID + " =?;");

            ps.setString(1, followerID.toString());
            ps.setString(2, followeeID.toString());
            ps.execute();
        }catch (SQLException e){
            System.out.println("Issue when unfollowing someone");
        }
    }

    /**
     * Gets list of people a user is following
     * */
    public static List<String> getFollowees(String userUuid){
        List<String> followees = new ArrayList<>();
        try {
            PreparedStatement ps = Main.sql.getCon().prepareStatement("SELECT " + USERNAME +
                    " FROM " + USER_TABLE +
                    " INNER JOIN "+ FOLLOWS_TABLE + " ON " + USER_TABLE +"." + USER_UUID + " =" + FOLLOWS_TABLE + "." + FOLLOWEE_ID +
                    " WHERE " + FOLLOWER_ID + " =?");
            ps.setString(1, userUuid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                followees.add(rs.getString(USERNAME));
            }

        } catch (SQLException e) {
            System.out.println("Error getting people someone follows");
        }
        return followees;

    }

    /**
     * Get numbers of followers
     * */
    public static int getFollowerCount(String username) throws SQLException {
        String uuid = getUUID(username);
        PreparedStatement ps = Main.sql.getCon().prepareStatement(
                "SELECT COUNT(" + FOLLOWER_ID +") FROM "+
                        FOLLOWS_TABLE + " WHERE " + FOLLOWEE_ID +" =?;"
        );
        ps.setString(1, uuid);

        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            return rs.getInt(1);
        }
        return -1;
    }


    /**
     * Get number of people following
     * */
    public static int getFolloweeCount(String username){
        int followeeCount = -1;
        try {
            String uuid = getUUID(username); //
            PreparedStatement ps = Main.sql.getCon().prepareStatement("SELECT COUNT(" +
                    FOLLOWEE_ID + ") FROM " + FOLLOWS_TABLE + " WHERE " +
                    FOLLOWER_ID + " =?;");

            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                followeeCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("error getting followee count");
        }
        return followeeCount;
    }


}
