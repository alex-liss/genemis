package net.genemis.calculator.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;

public class DateTimeUtilsTest {

    @Test
    public void testConvertToDaysFromEpoch() {

        List<ZonedDateTime> initial = List.of(
                ZonedDateTime.parse("1970-01-01T00:00:00Z"),
                ZonedDateTime.parse("1970-02-01T00:00:00Z"),
                ZonedDateTime.parse("1971-01-01T00:00:00Z")
        );

        List<Long> expected = List.of(0L, 31L, 365L);
        List<Long> actual = DateTimeUtils.convertToDaysFromEpoch(initial);
        Assertions.assertEquals(expected, actual);

    }
}
