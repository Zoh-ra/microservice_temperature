package com.zohra.microservice_temperature.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DateTimeUtils {

    // Arrondir vers le bas à l'intervalle de 10 minutes le plus proche
    public static LocalDateTime roundMinutesDownToNearestTen(LocalDateTime dateTime) {
        int minutes = dateTime.getMinute();
        int roundedMinutes = (minutes / 10) * 10;
        return dateTime.withMinute(roundedMinutes).withSecond(0).withNano(0);
    }

    // Générer la plage de dates en incluant la date de fin
    public static List<LocalDateTime> generateDateRange(LocalDateTime startDate, LocalDateTime endDate, int intervalMinutes) {
        List<LocalDateTime> dateRange = new ArrayList<>();
        LocalDateTime current = startDate;
        while (!current.isAfter(endDate)) {
            dateRange.add(current);
            current = current.plusMinutes(intervalMinutes);
        }
        return dateRange;
    }
}
