package backend.tunetracker;

import backend.tunetracker.cli.Commands;
import backend.tunetracker.config.DbConnection;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * */
public class Main {

    public static DbConnection sql;
    public static void main(String[] args) throws SQLException {
        sql = new DbConnection();

        Commands commands = new Commands();

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true){
            System.out.println("Enter a command - if stuck enter 'help' for list of commands");
            input = scanner.next().toLowerCase().trim();
            if (input.equals("q")) break;
            commands.parseInput(input);
        }

        sql.disconnect();

    }
}
