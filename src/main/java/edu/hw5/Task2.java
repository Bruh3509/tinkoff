package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private static final int FRIDAY_THIRTEEN = 13;

    public static List<LocalDate> getAllFridayThirteen(int year) {
        var currentDate = LocalDate.of(year, Month.JANUARY, 1);
        var firstFriday = TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY);
        currentDate = currentDate.with(firstFriday);

        List<LocalDate> resultDates = new ArrayList<>();
        while (currentDate.getYear() == year) {
            if (currentDate.getDayOfMonth() == FRIDAY_THIRTEEN) {
                resultDates.add(currentDate);
            }
            currentDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }

        return resultDates;
    }

    public static LocalDate getNearestFridayThirteen(LocalDate date) { // TODO
        var localDate = date;
        do {
            localDate = localDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
            if (localDate.getDayOfMonth() == FRIDAY_THIRTEEN) {
                return localDate;
            }
        } while (true);
    }

    private Task2() {
    }
}
