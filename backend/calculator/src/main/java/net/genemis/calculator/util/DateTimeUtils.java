package net.genemis.calculator.util;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class DateTimeUtils {

    private DateTimeUtils() {
    }

    /**
     * Converts a list of dates into a list of days starting from the epoch.
     *
     * @param dates list of dates
     * @return list of days from the epoch
     */
    public static List<Long> convertToDaysFromEpoch(List<ZonedDateTime> dates) {
        ZonedDateTime epoch = ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        return dates.stream().map(date -> ChronoUnit.DAYS.between(epoch, date)).toList();
    }
}
