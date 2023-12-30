package backend.tunetracker;

import backend.tunetracker.controller.Commands;
import backend.tunetracker.config.DbConnection;
import backend.tunetracker.seeddata.Loader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.Scanner;
import java.sql.SQLException;

/**
 * Main class to run program
 * */
public class Main {

    public static DbConnection sql;


    public static void main(String[] args) throws SQLException, CsvValidationException, IOException {
        sql = new DbConnection();

        Loader.loadDatabase();

        Commands commands = new Commands();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a command - if stuck enter 'help' for list of commands");
        while (sql.getCon()!=null){
            String input = scanner.nextLine().toLowerCase().trim();
            if (input.equals("q")) break;
            commands.parseInput(input);
        }

        sql.disconnect();

    }
}
