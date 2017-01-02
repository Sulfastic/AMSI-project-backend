package eu.needtocode.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

public class EmailSender {

    private static final String ERROR_DURING_SENDING_MESSAGE = "Error during sending message.";

    public void sendEmail(Message message) {
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(ERROR_DURING_SENDING_MESSAGE, e);
        }
    }
}
