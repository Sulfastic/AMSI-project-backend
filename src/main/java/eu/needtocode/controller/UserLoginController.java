package eu.needtocode.controller;

import eu.needtocode.model.LoginUserRQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static eu.needtocode.ApplicationMessages.INCORRECT_PASSWORD;
import static eu.needtocode.ApplicationMessages.LOGIN_SUCCESSFULLY;
import static eu.needtocode.ApplicationMessages.NO_USER;
import static eu.needtocode.ApplicationMessages.USER_IS_NOT_ACTIVE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("users/login")
public class UserLoginController {

    private final UsersService usersService;

    @Autowired
    public UserLoginController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(method = POST)
    public ResponseEntity<String> login(@RequestBody LoginUserRQ loginUserRQ) {
        LoginStatus loginStatus = usersService.login(loginUserRQ);

        switch (loginStatus) {
            case SUCCESS:
                return new ResponseEntity<>(String.format(LOGIN_SUCCESSFULLY, loginUserRQ.getNickname()), OK);
            case NOT_ACTIVE:
                return new ResponseEntity<>(String.format(USER_IS_NOT_ACTIVE, loginUserRQ.getNickname()), BAD_REQUEST);
            case NO_USER:
                return new ResponseEntity<>(String.format(NO_USER, loginUserRQ.getNickname()), BAD_REQUEST);
            case INCORRECT_PASSWORD:
                return new ResponseEntity<>(INCORRECT_PASSWORD, BAD_REQUEST);
            default:
                throw new RuntimeException("There is no LoginStatus specified.");
        }
    }
}
