package net.genemis.dispatcher.router;

import net.genemis.dispatcher.model.Message;
import net.genemis.dispatcher.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Configuration
public class MessageRouter {

    // TODO send key from FE
    private static final String GREETING_KEY = "greeting";

    private MessageService messageService;

    @Autowired
    public void setGreetingService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Bean
    public RouterFunction<ServerResponse> routeGreeting() {
        return RouterFunctions.route()
                .GET("/greeting", RequestPredicates.contentType(MediaType.APPLICATION_JSON), this::handleGreetingRequest)
                .build();
    }

    private Mono<ServerResponse> handleGreetingRequest(ServerRequest req) {
        Message message = messageService.getMessage(GREETING_KEY);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(message));
    }

}
