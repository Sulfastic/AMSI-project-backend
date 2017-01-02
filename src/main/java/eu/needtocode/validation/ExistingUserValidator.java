package eu.needtocode.validation;

import eu.needtocode.exception.ValidationException;
import eu.needtocode.model.AddUserRQ;
import eu.needtocode.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ExistingUserValidator implements ContextualValidator<List<User>, AddUserRQ> {

    static final String GIVEN_EMAIL_ADDRESS_IS_ALREADY_TAKEN = "Given email address is already taken.";
    static final String USER_WITH_GIVEN_NICKNAME_ALREADY_EXISTS = "User with given nickname already exists.";

    private static final Logger LOGGER = LoggerFactory.getLogger(ExistingUserValidator.class);

    @Override
    public void validate(List<User> users, AddUserRQ addUserRQ) {

        boolean invalidEmail = users.stream().anyMatch(user -> user.getEmail().equals(addUserRQ.getEmail()));
        if (invalidEmail) {
            LOGGER.info(GIVEN_EMAIL_ADDRESS_IS_ALREADY_TAKEN);
            throw new ValidationException(GIVEN_EMAIL_ADDRESS_IS_ALREADY_TAKEN);
        }
        boolean invalidNickname = users.stream().anyMatch(user -> user.getNickname().equals(addUserRQ.getNickname()));
        if (invalidNickname) {
            LOGGER.info(USER_WITH_GIVEN_NICKNAME_ALREADY_EXISTS);
            throw new ValidationException(USER_WITH_GIVEN_NICKNAME_ALREADY_EXISTS);
        }
    }

}
