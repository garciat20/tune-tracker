package backend.tunetracker.controller;

import backend.tunetracker.db.entity.PlaylistSql;
import backend.tunetracker.db.entity.SongSql;
import backend.tunetracker.db.entity.UserSql;
import backend.tunetracker.db.helpers.PrintStatement;
import backend.tunetracker.model.Song;
import backend.tunetracker.model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.sql.Date;

/**
 * Class that takes in user input and issues commands accordingly
 *
 * @author Thomas Garcia
 * */

public class Commands {
    private final String YELLOW = "\u001B[33m";
    private final String RESET = "\u001B[0m";
    private final String RED = "\u001B[31m";
    private final HashMap<String, Integer> COMMAND_REFERENCE = new HashMap<>(); // store possbile commands
    private final Scanner scanner = new Scanner(System.in); // by default take in CLI commands
    private User loggedIn; // commands are only issued if logged in


    private final String helpMessage = YELLOW+"""
            ================================================================================
            Commands: 
            q: quit/ close application
            login: enter credentials to log into account
            logout: to log out of current account
            create_user: to create a new account to login and use application functionality
            view_profile: view information about a user 
            follow_user: follower another user
            unfollow_user: unfollow another user
            view_followers: check out your followers
            view_followees: check out who you're following
            create_playlist: create a playlist
            view_playlist: view your own playlist(s)
            view_songs: view some random songs
            add_song_to_playlist: add a song to your own playlist
            help: to view commands to utilize database
            ================================================================================
            """+RESET;

    public Commands() {

        // make commands underscore instead of space for simplicility sake
        COMMAND_REFERENCE.put("help", 1);
        COMMAND_REFERENCE.put("login", 2);
        COMMAND_REFERENCE.put("logout", 3);
        COMMAND_REFERENCE.put("create_user", 4);
        COMMAND_REFERENCE.put("view_profile", 5);
        COMMAND_REFERENCE.put("follow_user", 6);
        COMMAND_REFERENCE.put("create_playlist", 7);
        COMMAND_REFERENCE.put("view_playlist", 8);
        COMMAND_REFERENCE.put("add_song_to_playlist", 9);
        COMMAND_REFERENCE.put("view_songs", 10);
        COMMAND_REFERENCE.put("unfollow_user", 11);
        COMMAND_REFERENCE.put("view_followers", 12);
        COMMAND_REFERENCE.put("view_followees", 13);

        this.loggedIn = null; // initialize commands in Main, so by default no one is logged in.
    }

    /**
     * Issues actions based on user input
     * */
    public void parseInput(String input) throws SQLException {
        Integer commandNumber = COMMAND_REFERENCE.get(input);
        commandNumber = (commandNumber != null) ? commandNumber : 0; // checking if 0, if so no valid command

        switch (commandNumber){
            case 1 -> System.out.print(this.helpMessage);
            case 2 -> login();
            case 3 -> logout();
            case 4 -> createUser();
            case 5 -> viewProfile();
            case 6 -> followUser();
            case 7 -> createPlaylist();
            case 8 -> viewPlaylist();
            case 9 -> addSongToPlaylist();
            case 10 -> viewSongs();
            case 11 -> unfollowUser();
            case 12 -> viewFollowers();
            case 13 -> viewFollowees();
            default -> System.out.println("Invalid command, enter 'help' for assistance");
        }
    }

    /**
     * Logs out a logged in user
     * */
    public void logout(){
        checkIfLoggedIn();
        this.loggedIn = null;
        System.out.println("You have logged out. Enter 'help' for more commands");
    }

    /**
     * Non Logged in user can log out
     * */
    public void createUser(){
        if (this.loggedIn != null) {
            System.out.println("You're already logged in! Enter 'logout' to create a new user!");
            return;
        }
        System.out.print("Enter first name: ");
        String first = this.scanner.nextLine().trim();
        System.out.print("Enter last name: ");
        String last = this.scanner.nextLine().trim();
        System.out.print("Enter username: ");
        String username = this.scanner.nextLine().trim();
        System.out.print("Enter email: ");
        String email = this.scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = this.scanner.nextLine().trim();

        LocalDate localDate = LocalDate.now();
        Date creationDate = Date.valueOf(localDate);

        User newUser = new User(UUID.randomUUID(),username,email,first,last,creationDate, creationDate);
        try {
            UserSql.insertUser(newUser, password);
            System.out.println("User created! Enter 'login' to use new account! Or enter 'help' for more commands");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error when creating new user :(");
        }
    }

    public void login() {
        if (this.loggedIn != null){
            System.out.println("You're already logged in! Enter 'help' for commands to enter!");
            return;
        }
        System.out.print("Enter email: ");
        String email = this.scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = DigestUtils.sha256Hex(this.scanner.nextLine().trim());
        try {
            this.loggedIn = UserSql.selectByEmailPassword(email,password);
            if (this.loggedIn != null){
                UserSql.updateLastAccessTime(this.loggedIn.getUuid()); // update the last time logged in for user
                System.out.println("Welcome back " + this.loggedIn.getUsername() + "! Enter 'help' for commands");
                return;
            }
        } catch (SQLException e) {
            System.out.println("oops something went wrong with sql");
        }
        System.out.println("Invalid credentials: either create a new account or retry logging in - 'help' for guidance");

    }

    /**
     * TODO: FINISH VIEWPROFILE
     * */
    public void viewProfile() throws SQLException {
        checkIfLoggedIn();
        System.out.print("Enter the username of who you want to search: ");
        String username = this.scanner.nextLine().trim();

        UserSql.viewProfile(username);
        System.out.println("Enter 'help' for more commands!");
    }

    /**
     * Prompt user to enter username of the user they wish to follow.
     * Get ID of follower and followee
     * With ID's use UserSQL class to enable the action
     * */
    public void followUser(){
        checkIfLoggedIn();
        System.out.print("Enter username you wish to follow: ");
        String username = this.scanner.nextLine().trim();
        try {
            String followeeUuid = UserSql.getUUID(username);
            UserSql.followPerson(this.loggedIn.getUuid(), UUID.fromString(followeeUuid));
        } catch (SQLException e) {
            System.out.println("cannot get UUID of user");
        }
        System.out.println("Sucessfully followed! Enter help for more commands!");


    }

    public void viewFollowers(){
        System.out.println("=============People following you=============");
        try {
            List<String> followers = UserSql.getFollowers(this.loggedIn.getUsername());

            for (int i =0; i < followers.size(); i++ ){
                System.out.println(followers.get(i));
            }
        } catch (SQLException e) {
            System.out.println("error with viewing followers");
        }
        System.out.println("Enter 'help' for more commands!");
    }

    public void viewFollowees(){
        System.out.println("=============People you're following=============");
        List<String> followees = UserSql.getFollowees(this.loggedIn.getUuid().toString());
        for (int i =0; i < followees.size(); i++){
            System.out.println(followees.get(i));
        }
        System.out.println("Enter 'help' for more commands!");

    }


    public void unfollowUser(){

        checkIfLoggedIn();

        System.out.print("Who do you wish to unfollow? ");
        String followee = this.scanner.nextLine().trim();

        UUID uuidOfFollowee;
        try {
            uuidOfFollowee = UUID.fromString(UserSql.getUUID(followee));
            System.out.println("DEBUG::: THIS SHOULD BE GONE CHECK DATAGRIP: " + uuidOfFollowee);
            UserSql.unfollowPerson(this.loggedIn.getUuid(), uuidOfFollowee);
        } catch (SQLException e) {
            System.out.println("Issue getting uuid of followee");
            return;
        }
        System.out.println("You unfollowed " + followee + "! Enter 'help' for more commands");
    }


    public void createPlaylist(){
        checkIfLoggedIn();
        if (this.loggedIn == null){
            System.out.println("You must be logged in");
            return;
        }
        System.out.print("Enter a name for your playlist: ");
        String playlistName = this.scanner.nextLine().trim();
        String uuidString = this.loggedIn.getUuid().toString();
        PlaylistSql.createUserPlaylist(playlistName, uuidString);
        System.out.println("Playlist created! Enter 'help' for more commands!");

    }

    public void viewPlaylist(){
        checkIfLoggedIn();
        // show songs from playlist and name of playlist

        System.out.println(RED+"CAN ONLY VIEW YOUR OWN PLAYLIST AT THE MOMENT"+ RESET);
        PrintStatement.printPlaylistHeaderFooter(PlaylistSql.getPlaylistNames(this.loggedIn.getUuid().toString()), this.loggedIn.getUsername());
//        System.out.println("Below are the following playlists that you have:");
        List<String> playlistNames = PlaylistSql.getPlaylistNames(this.loggedIn.getUuid().toString());

        // nothing else to do since playlist size is 0
        if (playlistNames.size() == 0){
            return;
        }
//        List<String> playlistNames = PlaylistSql.getPlaylistNames(this.loggedIn.getUuid().toString());
//        for (int i = 0; i < playlistNames.size(); i ++){
//            System.out.println(playlistNames.get(i));
//        }
        System.out.println("Would you like to see the songs from your playlist? (Enter " +
                "'y' or 'n'");
        String answer = this.scanner.nextLine().trim();

        if (!(answer.matches("y"))){
            System.out.println("Enter 'help' for more commands!");
            return;
        }
        System.out.print("Enter the playlist name you wish to view: ");
        // print out playlist(s) for a user based on user UUID
        String playlistName = this.scanner.nextLine().trim();

//        System.out.println("Do you wish to view your playlist? Enter 'yes' or 'no'");
//        String yesOrNo = this.scanner.nextLine().trim();
//        if (yesOrNo.matches("yes")){
            // below prints out the output

        // BELOW ONLY PRINTS OUT PLAYLIST SONGS FOR LOGGED IN USER
        int playlistId = PlaylistSql.getPlaylistId(playlistName,this.loggedIn.getUuid().toString());
        SongSql.viewSongsFromPlaylist(playlistId, this.loggedIn.getUuid().toString());
        System.out.println("Enter 'help' for more commands!");
        }

    /**
     * Works I'm 99% sure
     * */
    public void addSongToPlaylist(){
        checkIfLoggedIn();
        /**
         * make sure you preloaded data for a user to have a fake playlist
         * */
        List<String> playlistNames = PlaylistSql.getPlaylistNames(this.loggedIn.getUuid().toString());

        if (playlistNames.size()==0){
            System.out.println("No playlists were found, create one (create_playlist)! Enter 'help' for more commands");
            return;
        }
//        System.out.println("Which playlist would you like to add a song to? (your playlists are below): ");

        //method: generate a user's playlist, and get playlistId from playlist name

//        playlistNames.forEach(System.out::println);

        PrintStatement.printPlaylistHeaderFooter(playlistNames, loggedIn.getUsername());

        System.out.println("Enter the playlist name you wish to add songs to (BE EXACT): ");
        String playlistName = this.scanner.nextLine().trim();
        int playlistId = PlaylistSql.getPlaylistId(playlistName, this.loggedIn.getUuid().toString());

        System.out.println("What song would you like to add to your playlist? (5 random songs are below)");

        // method: generate 5 random songs
//        List<Song> = new LinkedList<>();
        // check how to create list and which one is perfereable based on performance

        List<Song> songs = SongSql.randomSongGenerator();
        for (int i = 0; i < songs.size(); i++){
            System.out.println(songs.get(i).getSongName());
        }
        System.out.print("Which song would you like to pick from the songs above? (Enter the song name exactly): ");

        // method: get songId from the song requested
        String songName = this.scanner.nextLine().trim();


        // method: get the pla
        int songId = SongSql.getSongId(songName);

        PlaylistSql.addSongToPlaylist(playlistId,songId);
        System.out.println("Check out the added song in your playlist! Enter 'help' for more commands");
    }

    public void viewSongs(){
        // not to show too much information just display 5 random songs (can have a while loop and break on command)
        System.out.println("Here are some random songs: ");
        List<Song> songs = SongSql.randomSongGenerator();
        for (int i = 0; i < songs.size(); i++){
            String songName = songs.get(i).getSongName();
            System.out.println(songName);
        }
        System.out.println("Enter 'help' for more commands!");
    }

    public void checkIfLoggedIn(){
        if (this.loggedIn == null){
            System.out.println("You must be logged in");
            return;
        }
    }

    /**
     * TODO: THE TWO METHODS BELOW ARE EASY TO DO
     * */
    public void renamePlaylist(){
        // self-explanatory
    }

    public void deletePlaylist(){
        // self-explanatory
    }

//    public void getUsernames(){
//        System.out.println("getting info");
//        // if works NEXT TRY OUT WITH =? PART OF PREPARED STATMENT
//        ArrayList<String> all_usernames = new ArrayList<>();
//        // trasnfer informatin for queries to the respective classes if it is more specfic to separate code
//
//        try {
//            PreparedStatement ps = Main.sql.getCon().prepareStatement("SELECT username from tunetracker.user ");
//            ResultSet queryResults = ps.executeQuery();
//            while (queryResults.next()){
//                String username = queryResults.getString("username");
//                all_usernames.add(username);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        for (String username: all_usernames){
//            System.out.println(username);
//        }
//    }
}
