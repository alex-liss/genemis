package net.genemis.dispatcher.service;

import net.genemis.dispatcher.model.Greeting;
import net.genemis.dispatcher.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class GreetingService {

    private GreetingRepository greetingRepository;

    @Autowired
    public void setGreetingRepository(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public Greeting getGreeting(String key) {
        List<Greeting> greetings = greetingRepository.findByKey(key);
        return greetings.stream().filter(greeting -> greeting.getLanguage().equals(Locale.ENGLISH.getLanguage())).findFirst().orElse(new Greeting());
    }


}
