package net.genemis.dispatcher.repository;

import net.genemis.util.DateConverters;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

@TestConfiguration
public class MongoClientTestConfig {

    @Bean
    public MongoCustomConversions customConversions(){
        List<Converter<?,?>> converters = new ArrayList<>();
        converters.add(new DateConverters.DateToZonedDateTimeConverter());
        converters.add(new DateConverters.ZonedDateTimeToDateConverter());
        return new MongoCustomConversions(converters);
    }
}
