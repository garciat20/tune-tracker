package backend.tunetracker;

import backend.tunetracker.controller.Commands;
import backend.tunetracker.config.DbConnection;
import java.util.Scanner;
import java.sql.SQLException;

/**
 * Main class to run program
 * */
public class Main {

    public static DbConnection sql;


    public static void main(String[] args) throws SQLException {
        sql = new DbConnection();

        Commands commands = new Commands();

        Scanner scanner = new Scanner(System.in);

        while (sql.getCon()!=null){
            System.out.println("Enter a command - if stuck enter 'help' for list of commands");
            String input = scanner.nextLine().toLowerCase().trim();
            if (input.equals("q")) break;
            commands.parseInput(input);
        }

        sql.disconnect();

    }
}
