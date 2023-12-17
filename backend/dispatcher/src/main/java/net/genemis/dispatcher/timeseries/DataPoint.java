package net.genemis.dispatcher.timeseries;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ts_brent_europe")
public class DataPoint implements Comparable<DataPoint> {

    @Field("date")
    @JsonFormat(with = {JsonFormat.Feature.WRITE_DATES_WITH_ZONE_ID})
    private ZonedDateTime utcDateTime;
    @Field("price")
    private double value;

    @Override
    public int compareTo(DataPoint o) {
        return utcDateTime.compareTo(o.utcDateTime);
    }
}
