package eu.needtocode.controller;

import eu.needtocode.dao.UserDao;
import eu.needtocode.mail.EmailCreator;
import eu.needtocode.mail.EmailSender;
import eu.needtocode.model.AddUserRQ;
import eu.needtocode.model.LoginUserRQ;
import eu.needtocode.model.User;
import eu.needtocode.validation.ExistingUserValidator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.Message;
import java.util.Collection;
import java.util.Optional;

import static eu.needtocode.controller.LoginStatus.INCORRECT_PASSWORD;
import static eu.needtocode.controller.LoginStatus.NOT_ACTIVE;
import static eu.needtocode.controller.LoginStatus.NO_USER;
import static eu.needtocode.controller.LoginStatus.SUCCESS;

public class UsersService {

    private final UserDao userDao;
    private final ExistingUserValidator existingUserValidator;
    private final EmailSender emailSender;
    private final EmailCreator emailCreator;

    @Autowired
    public UsersService(UserDao userRepositoryDao, ExistingUserValidator existingUserValidator, EmailSender emailSender, EmailCreator emailCreator) {
        this.userDao = userRepositoryDao;
        this.existingUserValidator = existingUserValidator;
        this.emailSender = emailSender;
        this.emailCreator = emailCreator;
    }

    public Collection<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void createNewUser(AddUserRQ addUserRQ) {
        existingUserValidator.validate(this.getAllUsers(), addUserRQ);
        String hash = RandomStringUtils.randomAlphanumeric(25);
        Message message = emailCreator.confirmationEmailMessage(addUserRQ.getNickname(), addUserRQ.getEmail(), hash);
        emailSender.sendEmail(message);

        userDao.addNewUser(User.createInactiveUser(
                addUserRQ.getNickname(),
                addUserRQ.getEmail(),
                addUserRQ.getPassword(),
                hash)
        );
    }

    public boolean activateUser(String hash) {
        Collection<User> allUsers = getAllUsers();
        Optional<User> userToActivate = allUsers.stream()
                .filter(User::isNotActive)
                .filter(user -> user.getHash().equals(hash))
                .findFirst();

        if (userToActivate.isPresent()) {
            User user = userToActivate.get();
            user.setActive(true);
            userDao.updateUser(user);

            return true;
        }
        return false;
    }

    public LoginStatus login(LoginUserRQ loginUserRQ) {
        Optional<User> userOptional = getAllUsers()
                .stream()
                .filter(user -> user.getNickname().equals(loginUserRQ.getNickname()))
                .findFirst();

        if (!userOptional.isPresent()) {
            return NO_USER;
        } else {
            User user = userOptional.get();
            if (user.isNotActive()) {
                return NOT_ACTIVE;
            }
            if (!user.getPassword().equals(loginUserRQ.getPassword())) {
                return INCORRECT_PASSWORD;
            }
            return SUCCESS;
        }

    }
}
