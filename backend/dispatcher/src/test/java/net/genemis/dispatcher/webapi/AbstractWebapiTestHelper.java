package net.genemis.dispatcher.webapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Abstract superclass for web api tests.
 */
@WebFluxTest
@AutoConfigureWebTestClient
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AbstractWebapiTestHelper {

    @Autowired
    protected ApplicationContext context;
    @Autowired
    protected WebTestClient webClient;

    protected ObjectMapper objectMapper;

    @BeforeAll
    protected void setUp() {
        webClient = WebTestClient.bindToApplicationContext(context).build();
        objectMapper = new ObjectMapper();
    }
}
