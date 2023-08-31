package net.genemis.dispatcher.service;

import net.genemis.dispatcher.model.Greeting;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public Greeting getGreeting() {
        return new Greeting();
    }

}
