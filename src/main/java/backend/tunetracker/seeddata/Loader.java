package backend.tunetracker.seeddata;

import backend.tunetracker.Main;
import backend.tunetracker.db.sql.PlaylistSql;
import backend.tunetracker.db.sql.SongSql;
import backend.tunetracker.db.sql.UserSql;
import backend.tunetracker.db.helpers.DateGenerator;
import backend.tunetracker.db.helpers.EnglishOnly;
import backend.tunetracker.db.helpers.MillisecondsConverter;
import backend.tunetracker.db.model.Song;
import backend.tunetracker.db.model.User;
import com.github.javafaker.Faker;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * Class that preloads data into database
 *
 * @author Thomas Garcia
 * */

public class Loader {
    /**
     * TODO: Create playlist for a dummyUser2 as mockdata
     * TODO: Create dummyUser2
     * */
    private static HashSet<String> artistNames = new HashSet<>();
    private static HashSet<String> uniqueUsernames = new HashSet<>();

    /**
     * Creates tables for schema
     * */
    public static void buildTables(){
        try {
            String userTable = "CREATE TABLE IF NOT EXISTS user(" +
                            "uuid CHAR(36) PRIMARY KEY, username VARCHAR(36) UNIQUE, password CHAR(64), email VARCHAR(36),"+
                            "last_name VARCHAR(30), first_name VARCHAR(30), creation_date DATE, last_access_date DATE" +
              ");";

            String followerTable = "CREATE TABLE IF NOT EXISTS follows(" +
                    "follower_id CHAR(36), followee_id CHAR(36), PRIMARY KEY (follower_id, followee_id)," +
                    "FOREIGN KEY (follower_id) REFERENCES user(uuid),"+
                    "FOREIGN KEY (followee_id) REFERENCES user(uuid));";

            String songsTables = "CREATE TABLE IF NOT EXISTS songs(" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, song_name VARCHAR(50) NOT NULL," +
                    "release_year DATE, duration TIME);";

            String artistTable = "CREATE TABLE IF NOT EXISTS artist("+
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(65));";

            String artistSongTable = "CREATE TABLE IF NOT EXISTS artist_songs ("+
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, artist_id INT NOT NULL,"+
                    "song_id INT NOT NULL, FOREIGN KEY (artist_id) REFERENCES artist(id)," +
                    "FOREIGN KEY (song_id) REFERENCES songs(id));"; // songs(id) --> songs(song_id)

            // reference exists in actual table

            // playlist table --> add to database
            // _________
            // |id||name||song_id (references song(id)|
            String playlistTable = "CREATE TABLE IF NOT EXISTS playlist(" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, playlist_name VARCHAR(32) UNIQUE," +
                    "user_id CHAR(36) NOT NULL," +
                    "FOREIGN KEY (user_id) REFERENCES user(uuid));";

            // song id and playlist id
            String songsInPlaylistTable = "CREATE TABLE IF NOT EXISTS songs_in_playlist(" +
                    "playlist_id INT NOT NULL, song_id INT NOT NULL," +
                    "FOREIGN KEY (playlist_id) REFERENCES playlist(id)," +
                    "FOREIGN KEY (song_id) REFERENCES songs(id));";

            PreparedStatement createUserTable = Main.sql.getCon().prepareStatement(userTable);
            PreparedStatement createFollowerTable = Main.sql.getCon().prepareStatement(followerTable);
            PreparedStatement createSongsTable = Main.sql.getCon().prepareStatement(songsTables);
            PreparedStatement createArtistTable = Main.sql.getCon().prepareStatement(artistTable);
            PreparedStatement createArtistSongTable = Main.sql.getCon().prepareStatement(artistSongTable);
            PreparedStatement createPlaylistTable = Main.sql.getCon().prepareStatement(playlistTable);
            PreparedStatement createSongsInPlaylistTable = Main.sql.getCon().prepareStatement(songsInPlaylistTable);

            createUserTable.execute();
            createFollowerTable.execute();
            createSongsTable.execute();
            createArtistTable.execute();
            createArtistSongTable.execute();
            createPlaylistTable.execute();
            createSongsInPlaylistTable.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * TODO: REFACTOR CODE TO UTILIZE SongsSql.java, Artistsql.java
     * */
    public static void loadMusic() throws CsvValidationException, IOException {
            CSVReader reader = new CSVReader(new FileReader("src/main/java/backend/tunetracker/excel/sample_songs.csv"));
            String[] line;
            // skip header

            reader.skip(1);
            while ((line = reader.readNext()) != null) {
                int artistKey = -1;
                int songKey = -1;
                String song = line[4];
                String millisecondsString= line[6];
                Time duration = MillisecondsConverter.duration(millisecondsString);

                if (EnglishOnly.isValidEnglish(song))
                    try {
                        // SONG TABLE
                        PreparedStatement songInsertion = Main.sql.getCon().prepareStatement(
                        "INSERT INTO songs(song_name, release_year, duration) VALUES(" +
                            "?, ?,?);",PreparedStatement.RETURN_GENERATED_KEYS
                        );

                        songInsertion.setString(1, song);
                        Date date = DateGenerator.dateGenerator();
//                            System.out.println(date.toString());
//                            System.out.println(date);
                        songInsertion.setDate(2, date);
                        songInsertion.setTime(3,duration);
                        songInsertion.execute();


                        // GET AUTO_INCREMENTED KEY USE LATER
                        ResultSet songKeyResult = songInsertion.getGeneratedKeys();

                        if (songKeyResult.next()){
                            songKey = songKeyResult.getInt(1);
                        } else {
                            throw new SQLException("no generated key found");
                        }

                        // ARTIST TABLE
                        String[] artistsParsed = line[2].split(";");

                        for (String artist: artistsParsed){

                            if (!artistNames.contains(artist)){
                                // SQL STATEMENT WITH SONG GENERATED KEY
                                PreparedStatement artistInsertion = Main.sql.getCon().prepareStatement(
                                        "INSERT INTO artist(name) VALUES (" +
                                                "?);", PreparedStatement.RETURN_GENERATED_KEYS
                                );
//                                System.out.println(artist);
                                artistInsertion.setString(1, artist);
                                artistInsertion.execute();

                                ResultSet artistKeyResult = artistInsertion.getGeneratedKeys();
                                if (artistKeyResult.next()){
                                    artistKey = artistKeyResult.getInt(1);
                                }  else {
                                    throw new SQLException("no generated key found");
                                };

                                // ARIST_SONGS TABLE AFTER WE GET ALL POSSIBLE ARTISTS FOR A SONG
                                PreparedStatement artistSongInsertion = Main.sql.getCon().prepareStatement(
                                        "INSERT INTO artist_songs(artist_id, song_id) VALUES (" +
                                                "?, ?);"
                                );
                                artistSongInsertion.setInt(1, artistKey);
                                artistSongInsertion.setInt(2, songKey);

                                artistSongInsertion.execute();

                                artistNames.add(artist); // now add to hashset so so we don't get duplicates
                            }
                        }
                    } catch (SQLException e){
                        throw new RuntimeException(e);
                    }
            }
    }


    /**
     * Loads users while making sure there's no duplicates
     * */
    public static void loadUsers() throws SQLException {
        Faker faker = new Faker();
        LocalDate lD = LocalDate.now();
        Date creationDate = Date.valueOf(lD);

        for (int i =0; i < 505; i++){
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String username = faker.name().username();
            String email = faker.internet().emailAddress();
            String password = faker.internet().password();

            if (!(uniqueUsernames.contains(username)) && (username.length() < 36) && (firstName.length() < 36) && (lastName.length() < 36) && (email.length() < 36) && (password.length() < 36)){
                User mockUser = new User(UUID.randomUUID(),username,email,firstName,lastName,creationDate, creationDate);
                try {
                    UserSql.insertUser(mockUser, password);
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("something went wrong with mock user");
                }
            }
            uniqueUsernames.add(username);
        }
    }

    /**
     * Creates a fake user that is the main character (has lots of data)
     * */
    public static void insertDummyUser() throws SQLException{
        LocalDate date = LocalDate.now();
        Date dateInsert = Date.valueOf(date);
        UUID dummyUserUuid = UUID.randomUUID();
        User dummy = new User(dummyUserUuid, "dummy", "dummy@gmail.com", "first name",
                "last name", dateInsert, dateInsert);
        UserSql.insertUser(dummy, "password");

        // loading fake playlist for dummy user

        String dummyPlaylistName = "dummy playlist";
        PlaylistSql.createUserPlaylist(dummyPlaylistName, dummyUserUuid.toString());
        int playlistId =  PlaylistSql.getPlaylistId(dummyPlaylistName, dummyUserUuid.toString());

        List<Song> songs = SongSql.randomSongGenerator();
        for (int i = 0; i < songs.size(); i++){
            String songName = songs.get(i).getSongName();
            int songId = SongSql.getSongId(songName);

            PlaylistSql.addSongToPlaylist(playlistId, songId);
        }
    }

    /**
     * Makes dummy have followers/ follow others | and anyone else have a follower/ follow someone
     * */
    public static void loadFollowers() throws SQLException {
        insertDummyUser();
        List<UUID> uuids = UserSql.getAllUuid();
        int counter = 0;
        String dummyUuid = UserSql.getUUID("dummy");
        while (counter < uuids.size()-1){
            UUID uuid1 = uuids.get(counter);
            counter ++;

            UUID uuid2 = uuids.get(counter);

            if (!(uuid1.toString().equals(dummyUuid))) {
                UserSql.followPerson(uuid1, UUID.fromString(dummyUuid)); //someone follows dummy
                UserSql.followPerson(UUID.fromString(dummyUuid),uuid1); //dummy follows someone
            }
            if (!(uuid2.toString().equals(dummyUuid))) {
                UserSql.followPerson(uuid2, UUID.fromString(dummyUuid)); //someone follows dummy
                UserSql.followPerson(UUID.fromString(dummyUuid),uuid2);// dummy follows someone
            }

            // anyone who isn't a dummy will be following someone and followed by that same person!
            if (!(uuid2.toString().equals(dummyUuid)) && !(uuid1.toString().equals(dummyUuid))) {
                UserSql.followPerson(uuid1,uuid2);
                UserSql.followPerson(uuid2,uuid1);
            }
            counter ++;
        }

    }



    public static void dropDatabase() throws SQLException {
        PreparedStatement ps = Main.sql.getCon().prepareStatement("DROP SCHEMA IF EXISTS tunetracker;");
        ps.executeUpdate();
    }

    public static void createDatabase() throws SQLException {
        PreparedStatement ps = Main.sql.getCon().prepareStatement("CREATE SCHEMA tunetracker;");
        ps.execute();
        PreparedStatement useDatabase = Main.sql.getCon().prepareStatement("USE tunetracker;");
        useDatabase.execute();
    }


    public static void loadDatabase() throws CsvValidationException, IOException, SQLException {
        System.out.println("loading database...");
        dropDatabase();
        createDatabase();
        buildTables(); // make sure tablesa rebuilt
        // load data
        loadMusic();
        loadUsers();
        loadFollowers();
        System.out.println("database loaded!");
    }

}
