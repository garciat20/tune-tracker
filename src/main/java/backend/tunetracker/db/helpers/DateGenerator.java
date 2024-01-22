package backend.tunetracker.db.helpers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Helper that creates random dates from 1970 to 2023
 *
 * @author Thomas Garcia
 * */
public class DateGenerator {
    public static Date dateGenerator(){
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2023, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        Date transformedDate = Date.valueOf(randomDate);
        return transformedDate;
    }
}
