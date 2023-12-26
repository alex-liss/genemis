package net.genemis.dispatcher.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import net.genemis.util.DateConverters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.*;

/**
 * Configuration for MongoDB client.
 */
@Configuration
public class MongoClientConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.host}")
    private String dbHost;
    @Value("${spring.data.mongodb.port}")
    private int dbPort;
    @Value("${spring.data.mongodb.database}")
    private String dbName;
    @Value("${spring.data.mongodb.username}")
    private String dbUser;
    @Value("${spring.data.mongodb.password}")
    private String dbPassword;

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(buildConnectionString());
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Override
    public Collection<String> getMappingBasePackages() {
        return Collections.singleton("net.genemis.dispatcher");
    }

    private String buildConnectionString() {
        return "mongodb://" + dbUser + ":" + dbPassword + "@" + dbHost + ":" + dbPort + "/" + dbName;
    }

    @Bean
    public MongoCustomConversions customConversions(){
        List<Converter<?,?>> converters = new ArrayList<>();
        converters.add(new DateConverters.DateToZonedDateTimeConverter());
        converters.add(new DateConverters.ZonedDateTimeToDateConverter());
        return new MongoCustomConversions(converters);
    }

/*    public static class DateToZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {
        @Override
        public ZonedDateTime convert(@NonNull Date source) {
            return DateConverters.toZonedDateTime(source);
        }
    }

    public static class ZonedDateTimeToDateConverter implements Converter<ZonedDateTime, Date> {
        @Override
        public Date convert(@NonNull ZonedDateTime source) {
            return DateConverters.toDate(source);
        }
    }*/

}
