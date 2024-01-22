package backend.tunetracker.db.helpers;

/**
 * Checks if something is in english
 *
 * @author Thomas Garcia
 * */
public class EnglishOnly {
    public static boolean isValidEnglish(String str){
        return str.matches("[a-zA-Z]+");
    }
}
