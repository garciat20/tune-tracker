package backend.tunetracker.db.helpers;

import java.sql.Time;
import java.time.LocalTime;

/**
 * Helper that converts milliseconds to a 00:00:00 format in Time form
 * */
public class MillisecondsConverter {
    public static Time duration(String milliseconds){
        int durationConverted = Integer.parseInt(milliseconds);

        int seconds = durationConverted / 1000;
        int hours = seconds / 3600;

        int remaningSecondsFromHour = seconds % 3600;
        // remaning seconds from hours then divide by 60
        int minutes = remaningSecondsFromHour / 60;

        // remaining seconds from minutes
        int remaining_seconds = seconds % 60;

        LocalTime timeNotSql = LocalTime.of(hours,minutes,remaining_seconds);
        Time sqlTime = Time.valueOf(timeNotSql);
        return sqlTime;
    }
}
