package com.example.rislaba4.jms;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@MessageDriven(mappedName = "jms/Topic", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class ConsumerSecond implements MessageListener {

    private final FileWriter writerForNumbers;
    private final FileWriter writerForLetters;
    public final Logger log = Logger.getLogger("ConsumerFirst");

    public ConsumerSecond() throws IOException {
        File numbers = new File("numbersSecond.txt");
        File letters = new File("lettersSecond.txt");

        if (!numbers.exists()) {
            log.log(Level.WARNING, "Is numbersSecond.txt created: " + numbers.createNewFile());
        } if (!letters.exists()) {
            log.log(Level.WARNING, "Is lettersSecond.txt created: " + letters.createNewFile());
        }

        writerForNumbers = new FileWriter(numbers);
        writerForLetters = new FileWriter(letters);

        log.log(Level.WARNING, "Destination of numbersSecond.txt: " + numbers.getAbsolutePath());
        log.log(Level.WARNING, "Destination of lettersSecond.txt: " + letters.getAbsolutePath());

        Runtime.getRuntime().exec("notepad " + numbers.getAbsolutePath());
        Runtime.getRuntime().exec("notepad " + letters.getAbsolutePath());
    }

    @Override
    public void onMessage(Message message) {
        try {
            handleMessage(((TextMessage) message).getText());
        } catch (JMSException | IOException e) {
            e.printStackTrace();
        }
    }

    private void handleMessage(String message) throws IOException {
        message.chars()
                .forEach(c -> {
                    try {
                        if (Character.isDigit(c)) {
                            writerForNumbers.append((char) c);
                        } else if (Character.isLetter(c)) {
                            writerForLetters.append((char) c);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        writerForLetters.flush();
        writerForNumbers.flush();

        writerForNumbers.write('\n');
        writerForLetters.write('\n');
    }
}
