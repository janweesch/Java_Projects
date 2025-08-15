package ToDo.UsefulMethods;

import java.time.DateTimeException;
import java.time.LocalDate;

public class TimeMethods
{
    /**
     * Sets a date from the given year, month, and day.
     * @param year of the date
     * @param month of the date (1-12)
     * @param dayOfMonth of the date (1-31)
     * @return the date in a {@code LocalDate} format
     * @throws DateTimeException if the date values are invalid
     */
    public static LocalDate setDate(int year, int month, int dayOfMonth) throws DateTimeException
    {
        return LocalDate.of(year, month, dayOfMonth);
    }

    public static class Repetition
    {
    }

    public static class Timer
    {

    }


}
