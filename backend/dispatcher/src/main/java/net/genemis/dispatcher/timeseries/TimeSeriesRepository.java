package net.genemis.dispatcher.timeseries;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.ZonedDateTime;
import java.util.List;

public interface TimeSeriesRepository extends MongoRepository<DataPoint, ZonedDateTime> {

    List<DataPoint> findAll();

}
