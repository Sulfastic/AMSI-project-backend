package eu.needtocode.mail;

import eu.needtocode.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class SessionFactory {

    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    private static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    private static final String MAIL_SMTP_HOST = "mail.smtp.host";
    private static final String MAIL_SMTP_PORT = "mail.smtp.port";


    @Autowired
    private ApplicationProperties applicationProperties;

    public Session getGmailSession() {
        Properties properties = new Properties();
        properties.put(MAIL_SMTP_AUTH, "true");
        properties.put(MAIL_SMTP_STARTTLS_ENABLE, "true");
        properties.put(MAIL_SMTP_HOST, "smtp.gmail.com");
        properties.put(MAIL_SMTP_PORT, "587");

        return Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(applicationProperties.getHostEmailAddress(), applicationProperties.getHostEmailPassword());
                    }
                });
    }
}
