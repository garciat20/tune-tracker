package backend.tunetracker.db.entity;

import backend.tunetracker.Main;
import backend.tunetracker.model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserSql {
    // Table(s)
    private static final String TABLE = "user";
    // Column(s)
    private static final String USER_UUID = "uuid";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String LAST_ACCESS_DATE = "last_access_date";
    private static final String CREATION_DATE = "creation_date";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    // INSERT INTO USERS(static final) VALUES ()
    //
    public static void insertUser(User user) throws SQLException{
        PreparedStatement ps = Main.sql.getCon().prepareStatement("INSERT INTO " +
                TABLE + "("+
                USER_UUID+ ", " + USERNAME + ", " + PASSWORD + ", " +
                EMAIL + ", " + LAST_NAME + ", " + FIRST_NAME + ", " +
                CREATION_DATE + ", " + LAST_ACCESS_DATE +
                ") VALUES (?,?,?,?,?,?,?,?)");
        ps.setString(1, user.getUuid().toString());
        ps.setString(2, user.getUsername());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getEmail());
        ps.setString(5, user.getLastName());
        ps.setString(6, user.getFirstName());
        ps.setDate(7, user.getCreationDate());
        ps.setDate(8,user.getLastAccessDate());
        ps.execute();
    }
}
