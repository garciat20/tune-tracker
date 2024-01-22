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
 *
 * @author Thomas Garcia
 * */
public class Main {
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String RESET = "\u001B[0m";
    public static DbConnection sql;


    public static void main(String[] args) throws SQLException, CsvValidationException, IOException {
        sql = new DbConnection();

        Loader.loadDatabase();

        Commands commands = new Commands();

        Scanner scanner = new Scanner(System.in);
        System.out.print(YELLOW+ """
                ==============================================================
                Enter a command - if stuck enter 'help' for list of commands! 
                """ + RESET + CYAN +
                """
                (SUGGESTION: USE A DUMMY USER)
                username: dummy
                email: dummy@gmail.com
                password: password
                """
                + RESET + YELLOW +
                """
                ==============================================================
                """ + RESET);
        while (sql.getCon()!=null){
            String input = scanner.nextLine().toLowerCase().trim();
            if (input.equals("q")) break;
            commands.parseInput(input);
        }

        sql.disconnect();

    }
}
