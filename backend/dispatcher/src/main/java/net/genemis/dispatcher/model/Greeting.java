package net.genemis.dispatcher.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@NoArgsConstructor
@Getter
@Setter
public class Greeting {

    private static final String DEFAULT_MESSAGE = "Welcome to Genemis";

    private String message;

    public String getMessage() {
        return Optional.ofNullable(message).orElse(DEFAULT_MESSAGE);
    }
}
