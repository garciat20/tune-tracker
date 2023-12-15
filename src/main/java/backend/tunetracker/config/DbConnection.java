package backend.tunetracker.config;
import java.sql.*;

public class DbConnection {
    private final String url = "jdbc:mysql://localhost:3306/tunetracker";
    private final String username = "root";
    private final String password = "root";
    private final String className = "com.mysql.jdbc.Driver";
    private Connection con;

    /**
     * Returns status of current connection
     *
     * @author Thomas Garcia
     * */
    public Connection getCon(){
        return this.con;
    }

    /**
     * Establishes connection to MySQL database
     *
     * @author Thomas Garcia
     * */
    public void connect(){
        try {
            Class.forName(className);
            this.con = DriverManager.getConnection(
                    url,username,password
            );
        } catch (Exception e) {
            System.out.println("Unable to connect to database :(");
        }
        System.out.println("Connected to tunetracker database!");
    }

    /**
     * Disconnects from MySQL database
     *
     * @author Thomas Garcia
     * */
    public void disconnect() throws SQLException {
        if (this.con!=null && !this.con.isClosed()){
            this.con.close();
            System.out.println("Disconnected from database, remember to close MySQL server running :)");
        }
    }
}
