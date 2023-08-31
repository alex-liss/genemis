package net.genemis.dispatcher.router;

import net.genemis.dispatcher.model.Greeting;
import net.genemis.dispatcher.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Configuration()
public class GreetingRouter {

    private GreetingService greetingService;

    @Autowired
    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Bean
    public RouterFunction<ServerResponse> routeGreeting() {
        return RouterFunctions.route()
                .GET("/greeting", RequestPredicates.contentType(MediaType.APPLICATION_JSON), this::handleGreetingRequest)
                .GET("/greeting", this::handlePlainGreetingRequest)
                .build();
    }

    private Mono<ServerResponse> handleGreetingRequest(ServerRequest req) {
        Greeting greeting = greetingService.getGreeting();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(greeting));
    }

    // TODO remove after frontend is connected
    private Mono<ServerResponse> handlePlainGreetingRequest(ServerRequest req) {
        Greeting greeting = greetingService.getGreeting();
        greeting.setMessage("Plain greeting");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(greeting));
    }
}
