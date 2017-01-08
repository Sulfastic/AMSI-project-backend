package eu.needtocode.controller;

import eu.needtocode.ApplicationMessages;
import eu.needtocode.model.LoginUserRQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/login")
public class UserLoginController {

    private final UsersService usersService;

    @Autowired
    public UserLoginController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(method = POST)
    public ResponseEntity<String> login(@RequestBody LoginUserRQ loginUserRQ) {
        boolean login = usersService.login(loginUserRQ);
        if (login) {
            return new ResponseEntity<>(ApplicationMessages.LOGIN_SUCCESSFULLY, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ApplicationMessages.CANNOT_LOGIN_USER, HttpStatus.BAD_REQUEST);
        }
    }
}
