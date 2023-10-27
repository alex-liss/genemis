package net.genemis.dispatcher.messages;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Optional;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "messages")
public class Message {

    // TODO send error if message is not found
    private static final String DEFAULT_MESSAGE = "Welcome to Genemis";

    private String message;
    private String language;

    public String getMessage() {
        return Optional.ofNullable(message).orElse(DEFAULT_MESSAGE);
    }
}
