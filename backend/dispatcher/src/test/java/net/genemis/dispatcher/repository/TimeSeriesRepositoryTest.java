package net.genemis.dispatcher.repository;

import net.genemis.dispatcher.timeseries.DataPoint;
import net.genemis.dispatcher.timeseries.TimeSeriesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Test class for the TimeSeriesRepository.
 *
 */
public class TimeSeriesRepositoryTest extends AbstractRepositoryTestHelper {

    @Autowired
    TimeSeriesRepository timeSeriesRepository;

    /**
     * Test for findAll() method of TimeSeriesRepository.
     * Should return only 4 data points, by which workday is true.
     */
    @Test
    public void testFindAll() {
        double[] expectedValues = {1.0, 2.0, 3.0, 4.0};
        List<DataPoint> actual = timeSeriesRepository.findAll();
        Assertions.assertEquals(4, actual.size());
        for (int i = 0; i < expectedValues.length; i++) {
            Assertions.assertEquals(expectedValues[i], actual.get(i).getValue());
        }
    }

    /**
     * Test for findByFirstDayOfMonth() method of TimeSeriesRepository.
     * Should return only 1 data point, by which date is first day of month.
     */
    @Test
    public void testFindByFirstDayOfMonth() {
        double expectedValue = 1.0d;
        List<DataPoint> actual = timeSeriesRepository.findByFirstDayOfMonth();
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals(expectedValue, actual.get(0).getValue());
    }


}
