package eu.needtocode.mail;

import eu.needtocode.ApplicationMessages;
import eu.needtocode.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.Message;
import javax.mail.MessagingException;

public class EmailCreator {

    private static final String CANNOT_CONSTRUCT_EMAIL_MESSAGE = "Cannot construct email message for: %s - %s";

    @Autowired
    private ApplicationProperties applicationProperties;
    @Autowired
    private SessionFactory sessionFactory;

    public Message confirmationEmailMessage(String name, String emailAddress, String hash) {
        try {
            return new MessageBuilder()
                    .withSubject(ApplicationMessages.CONFIRMATION_EMAIL_SUBJECT)
                    .withFrom(applicationProperties.getHostEmailAddress())
                    .withRecipients(emailAddress)
                    .withContent(String.format(ApplicationMessages.CONFIRMATION_EMAIL_CONTENT, name, hash))
                    .build(sessionFactory.getGmailSession());

        } catch (MessagingException e) {
            throw new RuntimeException(String.format(CANNOT_CONSTRUCT_EMAIL_MESSAGE, name, emailAddress));
        }
    }
}
