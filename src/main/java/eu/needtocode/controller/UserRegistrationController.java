package eu.needtocode.controller;

import eu.needtocode.model.AddUserRQ;
import eu.needtocode.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

import static eu.needtocode.ApplicationMessages.NEW_USER_ADDED;
import static eu.needtocode.ApplicationMessages.USER_ACTIVATION;
import static eu.needtocode.ApplicationMessages.USER_CANNOT_BE_ACTIVATED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("users")
public class UserRegistrationController {

    private final UsersService usersService;

    @Autowired
    public UserRegistrationController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(method = GET)
    public Collection<User> getAllUsers() {
        return usersService.getAllUsers();
    }

    @RequestMapping(method = POST, path = "register")
    public ResponseEntity<String> addUser(@RequestBody @Valid AddUserRQ addUserRQ) {
        usersService.createNewUser(addUserRQ);
        return new ResponseEntity<>(NEW_USER_ADDED, CREATED);
    }

    @RequestMapping(method = GET, path = "activate/{hash}")
    public ResponseEntity<String> activateUser(@PathVariable String hash) {
        if (usersService.activateUser(hash)) {
            return new ResponseEntity<>(USER_ACTIVATION, OK);
        } else {
            return new ResponseEntity<>(USER_CANNOT_BE_ACTIVATED, BAD_REQUEST);
        }
    }
}
