package eu.needtocode.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MessageBuilder {

    private String subject;
    private String content;
    private String recipients;
    private String from;

    public MessageBuilder withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public MessageBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public MessageBuilder withRecipients(String... recipients) {
        this.recipients = Arrays.stream(recipients).collect(Collectors.joining(";"));
        return this;
    }

    public MessageBuilder withFrom(String from) throws MessagingException {
        this.from = from;
        return this;
    }

    public Message build(Session session) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setSubject(subject);
        message.setText(content);
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(recipients));
        message.setFrom(new InternetAddress(from));
        return message;
    }
}
