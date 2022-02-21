package com.example.rislaba5.service;

import com.example.rislaba5.entity.Message;
import com.example.rislaba5.util.EntityManagerUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class MessageService {
    private final Logger log = Logger.getLogger("PersonService");
    private final EntityManager entityManager = EntityManagerUtil.getEntityManager();

    public void create(Message message) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(message);
            transaction.commit();
        } catch (Exception ignore) {
            transaction.rollback();
        }
    }

    public List<Message> readAll() {
        return (List<Message>) entityManager.createQuery("from Message ").getResultList();
    }

    public Message find(Long id) {
        return entityManager.find(Message.class, id);
    }

    public void update(Message newMessage) {
        Message message = entityManager.find(Message.class, newMessage.getId());

        if (message != null) {
            message.setReceiver(newMessage.getReceiver());
            message.setSender(newMessage.getSender());
            message.setPayload(newMessage.getPayload());
        }
    }

    public void delete(Long id) {
        Message message = entityManager.find(Message.class, id);
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.remove(message);
            transaction.commit();
        } catch (Exception ignore) {
            transaction.rollback();
        }
    }
}
