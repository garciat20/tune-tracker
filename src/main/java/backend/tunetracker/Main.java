package backend.tunetracker;

import backend.tunetracker.cli.Commands;
import backend.tunetracker.config.DbConnection;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * figure out of i connected properly and take a datapiece out from a simple table called test!
 * */
public class Main {

    public static void main(String[] args) {
        DbConnection connection = new DbConnection();

        connection.connect();

        Commands commands = new Commands();

        Scanner scanner = new Scanner(System.in);

        while (connection.getCon() != null){
            System.out.println("Enter a command - if stuck enter 'help' for list of commands");


            // get user input, based on that execute appropriate commands
            if (scanner.next() == "q"){
                break;
            }
            if (scanner.next() == "disconnect"){
                try {
                    connection.disconnect();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }

    }
}
