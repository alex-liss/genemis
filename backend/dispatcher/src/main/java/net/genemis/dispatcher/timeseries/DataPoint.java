package net.genemis.dispatcher.timeseries;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZonedDateTime;

@Data
@Document(collection = "ts_brent_europe")
public class DataPoint implements Comparable<DataPoint> {

    @Field("date")
    private ZonedDateTime utcDateTime;
    @Field("price")
    private double value;

    @Override
    public int compareTo(DataPoint o) {
        return utcDateTime.compareTo(o.utcDateTime);
    }
}
