package backend.tunetracker.controller;

import backend.tunetracker.Main;
import backend.tunetracker.db.entity.UserSql;
import backend.tunetracker.model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.*;
import java.sql.Date;

public class Commands {
    private final HashMap<String, Integer> command_reference = new HashMap<>(); // store possbile commands
    private final Scanner scanner = new Scanner(System.in); // by default take in CLI commands
    private User loggedIn; // commands are only issued if logged in
    private final String helpMessage = """
            Commands: 
            q: quit/ close application
            login: enter credentials to log into account
            create_user: to create a new account to login and use application functionality
            view_profile: view information about a user
            help: to view commands to utilize database
            """;

    public Commands() {
        // make commands underscore instead of space for simplicility sake
        command_reference.put("help", 1);
        command_reference.put("login", 2);
        command_reference.put("logout", 3);
        command_reference.put("create_user", 4);
        command_reference.put("view_profile", 5);
        command_reference.put("get_all_info", 6);

        this.loggedIn = null; // initialize commands in Main, so by default no one is logged in.
    }

    public void parseInput(String input){
        Integer commandNumber = command_reference.get(input);
        commandNumber = (commandNumber != null) ? commandNumber : 0; // checking if 0, if so no valid command

        switch (commandNumber){
            case 1 -> System.out.println(this.helpMessage);
            case 2 -> login();
            case 3 -> logout();
            case 4 -> createUser();
            case 5 -> viewProfile();
            case 6 -> getUsernames();
            default -> System.out.println("Invalid command, enter 'help' for assistance");
        }
    }

    public void logout(){
        // update last access time
        this.loggedIn = null;
    }

    public void createUser(){
        if (this.loggedIn != null) {
            System.out.println("You're already logged in! Enter 'logout' to create a new user!");
            return;
        }
        System.out.println("Enter first name");
        String first = this.scanner.nextLine().trim();
        System.out.println("Enter last name");
        String last = this.scanner.nextLine().trim();
        System.out.println("Enter username");
        String username = this.scanner.nextLine().trim();
        System.out.println("Enter email");
        String email = this.scanner.nextLine().trim();
        System.out.println("Enter password");
        String password = DigestUtils.sha256Hex(this.scanner.nextLine().trim());
//        LocalDate create_date = LocalDate.now(); //
        long currentTimeInMillis = System.currentTimeMillis();
        Date creationDate = new Date(currentTimeInMillis);
        User newUser = new User(UUID.randomUUID(),username,password,email,first,last,creationDate, creationDate);
        try {
            UserSql.insertUser(newUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void login(){
        if (this.loggedIn != null){
            System.out.println("You're already logged in! Enter 'help' for commands to enter!");
            return;
        }
        System.out.print("Enter email: ");
        String email = this.scanner.nextLine().trim();
        System.out.println();
        System.out.print("Enter password: ");
        String password = DigestUtils.sha256Hex(this.scanner.nextLine().trim());
        System.out.println();
        // make a query to compare email and passwords --> if valid set it to new user
        // check if user is still null, if it is, invalid credentials
        // if user is valid --> update access time
        // if



    }

    public void viewProfile(){

    }

    public void getUsernames(){
        System.out.println("getting info");
        // if works NEXT TRY OUT WITH =? PART OF PREPARED STATMENT
        ArrayList<String> all_usernames = new ArrayList<>();
        // trasnfer informatin for queries to the respective classes if it is more specfic to separate code

        try {
            PreparedStatement ps = Main.sql.getCon().prepareStatement("SELECT username from tunetracker.user ");
            ResultSet queryResults = ps.executeQuery();
            while (queryResults.next()){
                String username = queryResults.getString("username");
                all_usernames.add(username);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String username: all_usernames){
            System.out.println(username);
        }
    }
}
