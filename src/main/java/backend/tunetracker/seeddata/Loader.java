package backend.tunetracker.seeddata;

import backend.tunetracker.Main;
import com.github.javafaker.Faker;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Loader {

    public static void databaseMaker(){
        try {
            Statement statement = Main.sql.getCon().createStatement();
            String databaseQuery = "CREATE DATABASE IF NOT EXISTS tunetracker;";
            statement.execute(databaseQuery);

            String useDatabase = "USE tunetracker";
            statement.execute(useDatabase);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void buildTables(){
        try {
            String userTable = "CREATE TABLE IF NOT EXISTS user(" +
                            "uuid CHAR(36) PRIMARY KEY, username VARCHAR(16), password CHAR(64), email VARCHAR(36),"+
                            "last_name VARCHAR(30), first_name VARCHAR(30), creation_date DATE, last_access_date DATE" +
              ");";

            String followerTable = "CREATE TABLE IF NOT EXISTS follows(" +
                    "follower_id CHAR(36), followed_id CHAR(36), PRIMARY KEY (follower_id, followed_id)," +
                    "FOREIGN KEY (follower_id) REFERENCES user(uuid),"+
                    "FOREIGN KEY (followed_id) REFERENCES user(uuid));";

            String songsTables = "CREATE TABLE IF NOT EXISTS songs(" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, song_name VARCHAR(36) NOT NULL, artist VARCHAR(36) NOT NULL," +
                    "release_year DATE);";


            PreparedStatement createUserTable = Main.sql.getCon().prepareStatement(userTable);
            PreparedStatement createFollowerTable = Main.sql.getCon().prepareStatement(followerTable);
            PreparedStatement createSongsTable = Main.sql.getCon().prepareStatement(songsTables);

            createUserTable.execute();
            createFollowerTable.execute();
            createSongsTable.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void loadMusic(){

    }

    public static void loadUsers(){
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String username = faker.name().username();

    }

    public static void loadDatabase(){
        databaseMaker(); //make sure we have database
        buildTables(); // make sure tablesa rebuilt
        // load data
//        loadMusic();
//        loadUsers();
    }

}
