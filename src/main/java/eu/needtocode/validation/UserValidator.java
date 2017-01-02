package eu.needtocode.validation;

import eu.needtocode.exception.ValidationException;
import eu.needtocode.model.AddUserRQ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserValidator implements Validator<AddUserRQ> {

    static final String USER_EMAIL_SHOULD_CONTAINS_SIGN = "User email should contains '@' sign.";

    private static final Logger LOGGER = LoggerFactory.getLogger(UserValidator.class);

    @Override
    public void validate(AddUserRQ userRQ) {
        if (!userRQ.getEmail().contains("@")) {
            throw new ValidationException(USER_EMAIL_SHOULD_CONTAINS_SIGN);
        }
        LOGGER.info(userRQ.getEmail() + " is a valid email address.");
    }
}
