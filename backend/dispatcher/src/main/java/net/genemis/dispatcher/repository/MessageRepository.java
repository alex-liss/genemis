package net.genemis.dispatcher.repository;

import net.genemis.dispatcher.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {

    @Query(value = "{ 'key' : ?0 }")
    List<Message> findByKey(String key);
}
