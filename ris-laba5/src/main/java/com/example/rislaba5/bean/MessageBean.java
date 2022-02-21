package com.example.rislaba5.bean;

import com.example.rislaba5.entity.Message;
import com.example.rislaba5.entity.Person;
import com.example.rislaba5.service.MessageService;
import com.example.rislaba5.service.PersonService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jdk.jfr.Name;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class MessageBean implements Serializable {
    private Logger logger = Logger.getLogger("MessageBean");
    @Inject
    private MessageService messageService;
    @Inject
    private PersonService personService;
    private List<Message> messages;
    @Getter @Setter
    private Message messageToAdd = makeBlankMessage();
    @Getter @Setter
    private String searchString = "";

    public List<Message> getMessages() {
        messages = messageService.readAll();
        return messages.stream()
                .filter(message -> message.getId().toString().contains(searchString)
                                || message.getReceiver().getId().toString().contains(searchString)
                                || message.getSender().getId().toString().contains(searchString)
                                || message.getPayload().contains(searchString))
                .toList();
    }

    public List<Person> getPeople() {
        return personService.readAll();
    }

    public String editMessage(Message message) {
        messageService.update(message);

        return "MESSAGES";
    }

    public String deleteMessage(Message message) {
        messageService.delete(message.getId());

        return "MESSAGES";
    }

    public String addMessage() {
        logger.log(Level.WARNING, messageToAdd.toString());
        Person sender = personService.read(messageToAdd.getSender().getId());
        Person receiver = personService.read(messageToAdd.getReceiver().getId());

        messageToAdd.setSender(sender);
        messageToAdd.setReceiver(receiver);

        messageService.create(messageToAdd);
        messageToAdd = makeBlankMessage();

        return "MESSAGES";
    }

    private Message makeBlankMessage() {
        return new Message(
                null,
                new Person(),
                new Person(),
                null
        );
    }
}
