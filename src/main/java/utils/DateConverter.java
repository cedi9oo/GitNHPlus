package utils;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This class is used to convert strings to a date or time specification.
 */
public class DateConverter {

    /**
     * This method is for converting strings to a date
     * @param date
     * @return result of converting
     */
    public static LocalDate convertStringToLocalDate(String date) {
        String[] array = date.split("-");
        LocalDate result = LocalDate.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]),
                Integer.parseInt(array[2]));
        return result;
    }

    /**
     * This method is for converting strings at a time
     * @param time
     * @return result of converting
     */
    public static LocalTime convertStringToLocalTime(String time) {
        String[] array = time.split(":");
        LocalTime result = LocalTime.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
        return result;
    }
}
