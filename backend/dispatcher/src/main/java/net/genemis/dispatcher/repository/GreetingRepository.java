package net.genemis.dispatcher.repository;

import net.genemis.dispatcher.model.Greeting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface GreetingRepository extends MongoRepository<Greeting, String> {

    @Query(value = "{ 'key' : ?0 }")
    List<Greeting> findByKey(String key);
}
