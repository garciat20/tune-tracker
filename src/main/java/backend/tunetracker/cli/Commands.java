package backend.tunetracker.cli;

import backend.tunetracker.Main;

import java.net.StandardSocketOptions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Commands {
    // map for commands
    private final HashMap<String, Integer> command_reference = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    public Commands() {
        // make commands underscore instead of space for simplicility sake
        command_reference.put("get_all_info", 1);
    }

    public void parseInput(String input){
        Integer commandNumber = command_reference.get(input);
        commandNumber = (commandNumber != null) ? commandNumber : 0; // checking if 0, if so no valid command

        switch (commandNumber){
            case 1 -> this.getUsernames();
            default -> System.out.println("Invalid command, enter 'help' for assistance");
        }
    }
//    public ArrayList<String> getUsername(){
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
