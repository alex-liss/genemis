package net.genemis.dispatcher.timeseries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class TimeSeriesService {

    private TimeSeriesRepository timeSeriesRepository;

    @Autowired
    public void setTimeSeriesRepository(TimeSeriesRepository timeSeriesRepository) {
        this.timeSeriesRepository = timeSeriesRepository;
    }

    public Set<DataPoint> getTimeSeries() {
        List<DataPoint> data = timeSeriesRepository.findByFirstDayOfMonth();
        return new TreeSet<>(data);
    }
}
