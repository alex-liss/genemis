package net.genemis.dispatcher.timeseries;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.TreeSet;

@Data
@NoArgsConstructor
public class TimeSeries {

    private TreeSet<DataPoint> dataPoints = new TreeSet<>();

    public TimeSeries(List<DataPoint> dataPoints) {
        this.dataPoints = new TreeSet<>(dataPoints);
    }

    public void addDataPoints(List<DataPoint> dataPoints) {
        this.dataPoints.addAll(dataPoints);
    }
}
