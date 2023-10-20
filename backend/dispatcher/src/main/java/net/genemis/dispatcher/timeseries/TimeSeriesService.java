package net.genemis.dispatcher.timeseries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeSeriesService {

    private TimeSeriesRepository timeSeriesRepository;

    @Autowired
    public void setTimeSeriesRepository(TimeSeriesRepository timeSeriesRepository) {
        this.timeSeriesRepository = timeSeriesRepository;
    }

    public TimeSeries getTimeSeries() {
        List<DataPoint> data = timeSeriesRepository.findAll();
        return new TimeSeries(data);
    }
}
