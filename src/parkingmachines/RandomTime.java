package parkingmachines;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

/**
 * Helper class that creates random check in and check out times.
 */
public abstract class RandomTime {
    private static Random r = new Random();
    private static LocalDate date = LocalDate.now();

    /**
     * Creates a random check in time between 7am and noon.
     * @return The check in time
     */
    public static LocalDateTime makeRandomCheckInTime() {
        int hour = r.nextInt(5) + 7;
        int minute = r.nextInt(60);
        LocalTime time = LocalTime.of(hour, minute);

        return LocalDateTime.of(date, time);
    }

    /**
     * Creates a random check out time between 1pm and 11pm.
     * @return The check out time
     */
    public static LocalDateTime makeRandomCheckOutTime() {
        int hour = r.nextInt(11) + 13;
        int minute = r.nextInt(60);
        LocalTime time = LocalTime.of(hour, minute);

        return LocalDateTime.of(date, time);
    }

}
