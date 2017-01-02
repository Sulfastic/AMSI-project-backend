package eu.needtocode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class ApplicationProperties {

    private static final String AMSI_PROJECT_HOST_EMAIL_ADDRESS = "amsi.project.hostEmailAddress";
    private static final String AMSI_PROJECT_HOST_EMAIL_PASSWORD = "amsi.project.hostEmailPassword";

    @Autowired
    private Environment environment;

    public String getHostEmailAddress() {
        return environment.getProperty(AMSI_PROJECT_HOST_EMAIL_ADDRESS);
    }

    public String getHostEmailPassword() {
        return environment.getProperty(AMSI_PROJECT_HOST_EMAIL_PASSWORD);
    }
}
