package eu.needtocode.config;

import eu.needtocode.ApplicationProperties;
import eu.needtocode.mail.EmailCreator;
import eu.needtocode.mail.EmailSender;
import eu.needtocode.mail.SessionFactory;
import eu.needtocode.validation.ExistingUserValidator;
import eu.needtocode.validation.UserValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public ApplicationProperties applicationProperties() {
        return new ApplicationProperties();
    }

    @Bean
    public UserValidator userValidator() {
        return new UserValidator();
    }

    @Bean
    public ExistingUserValidator existingUserValidator() {
        return new ExistingUserValidator();
    }

    @Bean
    public EmailSender emailSender() {
        return new EmailSender();
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new SessionFactory();
    }

    @Bean
    public EmailCreator emailCreator() {
        return new EmailCreator();
    }
}
