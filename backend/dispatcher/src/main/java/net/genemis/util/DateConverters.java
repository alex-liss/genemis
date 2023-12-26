package net.genemis.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Utility class for converting {@link Date} to {@link ZonedDateTime} and vice versa.
 */
public final class DateConverters {

    private DateConverters() {
    }

    public static class DateToZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {
        @Override
        public ZonedDateTime convert(@NonNull Date source) {
            return toZonedDateTime(source);
        }
    }

    public static class ZonedDateTimeToDateConverter implements Converter<ZonedDateTime, Date> {
        @Override
        public Date convert(@NonNull ZonedDateTime source) {
            return toDate(source);
        }
    }

    public static Date toDate(ZonedDateTime zonedDateTime) {
        return null != zonedDateTime ? Date.from(zonedDateTime.toInstant()) : null;
    }

    public static ZonedDateTime toZonedDateTime(Date date) {
        return null != date ? date.toInstant().atZone(ZoneOffset.UTC) : null;
    }

}
