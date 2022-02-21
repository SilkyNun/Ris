package com.example.rislaba4.jms;


import jakarta.annotation.Resource;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;
import jakarta.jms.*;

@Stateless
@LocalBean
public class Producer {
    @Resource(mappedName = "jms/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/Topic")
    private Destination destination;

    public void produce(String message) {
        try (Connection connection = connectionFactory.createConnection();
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)){

            MessageProducer producer = session.createProducer(destination);
            producer.send(session.createTextMessage(message));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
