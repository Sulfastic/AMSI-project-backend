package eu.needtocode.controller;

import eu.needtocode.dao.UsersRepository;
import eu.needtocode.mail.EmailCreator;
import eu.needtocode.mail.EmailSender;
import eu.needtocode.model.AddUserRQ;
import eu.needtocode.model.User;
import eu.needtocode.validation.ExistingUserValidator;
import eu.needtocode.validation.UserValidator;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Message;
import java.util.List;
import java.util.Optional;

import static eu.needtocode.ApplicationMessages.NEW_USER_ADDED;
import static eu.needtocode.ApplicationMessages.USER_ACTIVATION;
import static eu.needtocode.ApplicationMessages.USER_CANNOT_BE_ACTIVATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("users")
public class UsersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private ExistingUserValidator existingUserValidator;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private EmailCreator emailCreator;

    @RequestMapping(method = GET)
    public List<User> getAllUsers() {
        LOGGER.info("Getting all users.");
        return usersRepository.findAll();
    }

    @RequestMapping(method = POST, path = "/add")
    public String addUser(@RequestBody AddUserRQ addUserRQ) {
        userValidator.validate(addUserRQ);
        existingUserValidator.validate(getAllUsers(), addUserRQ);
        String hash = RandomStringUtils.randomAlphanumeric(25);
        Message message = emailCreator.confirmationEmailMessage(addUserRQ.getNickname(), addUserRQ.getEmail(), hash);
        emailSender.sendEmail(message);

        usersRepository.save(User.createInactiveUser(
                addUserRQ.getNickname(),
                addUserRQ.getEmail(),
                addUserRQ.getPassword(),
                hash)
        );

        return NEW_USER_ADDED;
    }

    @RequestMapping(method = GET, path = "activate/{hash}")
    public String activateUser(@PathVariable String hash) {
        List<User> allUsers = getAllUsers();
        Optional<User> userToActivate = allUsers.stream()
                .filter(User::isNotActive)
                .filter(user -> user.getHash().equals(hash))
                .findFirst();

        if (userToActivate.isPresent()) {
            User user = userToActivate.get();
            user.setActive(true);
            usersRepository.save(user);
            return USER_ACTIVATION;
        } else {
            return USER_CANNOT_BE_ACTIVATED;
        }
    }
}
