package net.genemis.util;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateUtils {

    private DateUtils() {
    }

    public static Date toDate(ZonedDateTime zonedDateTime) {
        return null != zonedDateTime ? Date.from(zonedDateTime.toInstant()) : null;
    }

    public static ZonedDateTime toZonedDateTime(Date date) {
        return null != date ? date.toInstant().atZone(ZoneOffset.UTC) : null;
    }
}
