package net.genemis.dispatcher.repository;

import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

/**
 * Abstract superclass for repository tests.
 */
@Testcontainers
@EnableMongoRepositories(basePackages = "net.genemis.dispatcher")
@ContextConfiguration(classes = {MongoClientTestConfig.class})
@DataMongoTest
public class AbstractRepositoryTestHelper {

    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0")
            .withReuse(true)
            .withCopyFileToContainer(MountableFile.forClasspathResource("init-db.js"),"/docker-entrypoint-initdb.d/init-db.js");
    static {
        try {
            mongoDBContainer.start();
        } catch (Exception e) {
            System.out.println(mongoDBContainer.getLogs());
            throw e;
        }
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }
}
