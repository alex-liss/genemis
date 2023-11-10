package net.genemis.dispatcher.timeseries;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.ZonedDateTime;
import java.util.List;

public interface TimeSeriesRepository extends MongoRepository<DataPoint, ZonedDateTime> {

    @Query("{ 'workday' : true }")
    List<DataPoint> findAll();

    @Query("{ $expr: { $eq: [{ $dayOfMonth: '$date' }, 1] } }")
    List<DataPoint> findByFirstDayOfMonth();
}
