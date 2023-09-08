package net.genemis.dispatcher.service;

import net.genemis.dispatcher.model.Message;
import net.genemis.dispatcher.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message getMessage(String key) {
        List<Message> messages = messageRepository.findByKey(key);
        // TODO get language from request
        return messages.stream().filter(message -> message.getLanguage().equals(Locale.ENGLISH.getLanguage())).findFirst().orElse(new Message());
    }


}
