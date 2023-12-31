package backend.tunetracker.db.entity;

import backend.tunetracker.Main;
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

public class UserSql {
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
    // INSERT INTO USERS(static final) VALUES ()
    //
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

    public static void viewProfile(String username)throws SQLException{
        PreparedStatement ps = Main.sql.getCon().prepareStatement("SELECT "+ FOLLOWER_ID + " FROM "
        + FOLLOWS_TABLE +" WHERE followee_id =?;");

        String uuidFromUsername = getUUID(username); // get uuid

        ps.setString(1, uuidFromUsername); // set it from uuid
        ResultSet rs = ps.executeQuery(); // results from sql table

        System.out.println("Followers of " + username + ": "); // traverse thru sql
        // finish below
        while (rs.next()){
            String follower = getUsername(rs.getString(USER_UUID));
            System.out.println(follower);
        }

    }

    public static String getUUID(String username) throws SQLException {
        PreparedStatement ps = Main.sql.getCon().prepareStatement("SELECT " + USER_UUID + " FROM " + USER_TABLE +
                " WHERE "+ USERNAME + " =?;");
        ps.setString(1,username);
        ResultSet rs = ps.executeQuery();

        /**
         * ERROR BELOW DEBUG
         * */
        String uuid = rs.getString(USER_UUID);
        return uuid;
    }

    public static String getUsername(String uuid)throws SQLException{
        PreparedStatement ps = Main.sql.getCon().prepareStatement(
                "SELECT " + USERNAME + " FROM " + USER_TABLE + " WHERE " + USER_UUID +" =?;"
        );
        ps.setString(1, uuid);
        ResultSet rs = ps.executeQuery();
        String username = rs.getString(USERNAME);
        return username;
    }

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
            throw new RuntimeException(e);
        }
    }



}
