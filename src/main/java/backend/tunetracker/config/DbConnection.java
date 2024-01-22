package backend.tunetracker.config;
import java.sql.*;

/**
 *  Class to establish a connection to the database
 *
 * @author Thomas Garcia
 * */

public class DbConnection {
    private static final String YELLOW = "\u001B[33m";
    private static final String RESET = "\u001B[0m";
    private final String url = "jdbc:mysql://localhost:3306/tunetracker";
    private final String username = "root";
    private final String password = "root";
    private final String className = "com.mysql.cj.jdbc.Driver";
    private Connection con;

    /**
     * Establishes connection to MySQL database
     *
     * @author Thomas Garcia
     * */
    public DbConnection() {
        try {
            Class.forName(this.className);
            this.con = DriverManager.getConnection(
                    this.url, this.username, this.password
            );
        } catch (Exception e) {
            System.out.println("Unable to connect to database :(");
            e.printStackTrace();
            return;
        }
        System.out.println("Connected to tunetracker database!");
    }


    /**
     * Returns status of current connection
     *
     * @author Thomas Garcia
     * */
    public Connection getCon() {
        return this.con;

    }

    /**
     * Disconnects from MySQL database
     *
     * @author Thomas Garcia
     * */
    public void disconnect() throws SQLException {
        if (this.con!=null && !this.con.isClosed()){
            this.con.close();
            System.out.println(YELLOW + "Disconnected from database, remember to close MySQL server running :)" + RESET);
        }
    }
}
