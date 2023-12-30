package backend.tunetracker.db.helpers;

public class EnglishOnly {
    public static boolean isValidEnglish(String str){
        return str.matches("[a-zA-Z]+");
    }
}
