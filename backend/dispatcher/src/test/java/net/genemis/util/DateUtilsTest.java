package net.genemis.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.TimeZone;

public class DateUtilsTest {

    @Test
    public void testToDate() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(ZoneOffset.UTC));
        calendar.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Assertions.assertEquals(calendar.getTime(), DateUtils.toDate(zonedDateTime));
    }

    @Test
    public void testToDate_NullParam() {
        Assertions.assertNull(DateUtils.toDate(null));
    }

    @Test
    public void testToZonedDateTime() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(ZoneOffset.UTC));
        calendar.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Assertions.assertEquals(zonedDateTime, DateUtils.toZonedDateTime(calendar.getTime()));
    }

    @Test
    public void testToZonedDateTime_NullParam() {
        Assertions.assertNull(DateUtils.toZonedDateTime(null));
    }
}
