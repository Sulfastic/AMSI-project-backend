package eu.needtocode.validation;

import eu.needtocode.exception.ValidationException;
import eu.needtocode.model.AddUserRQ;
import eu.needtocode.model.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static eu.needtocode.validation.ExistingUserValidator.*;
import static org.fest.util.Collections.list;

public class ExistingUserValidatorTest {

    private ExistingUserValidator existingUserValidator = new ExistingUserValidator();
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldThrowExceptionWhenGivenEmailAlreadyExists() {
        // given
        List<User> existingUsers = list(user("asd@asd.com", "nick1"));
        AddUserRQ addUserRQ = addUserRQ("asd@asd.com", "qwerty");

        // expect
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage(GIVEN_EMAIL_ADDRESS_IS_ALREADY_TAKEN);

        // when
        existingUserValidator.validate(existingUsers, addUserRQ);
    }

    @Test
    public void shouldThrowExceptionWhenGivenNicknameAlreadyExists() {
        // given
        List<User> existingUsers = list(user("aaa@asd.com", "nick1"));
        AddUserRQ addUserRQ = addUserRQ("bbb@asd.com", "nick1");

        // expect
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage(USER_WITH_GIVEN_NICKNAME_ALREADY_EXISTS);

        // when
        existingUserValidator.validate(existingUsers, addUserRQ);
    }

    @Test
    public void shouldNotThrowAnyExceptions() {
        // given
        List<User> existingUsers = list(user("aaa@asd.com", "aaa1"));
        AddUserRQ addUserRQ = addUserRQ("bbb@asd.com", "bbb1");

        // when
        existingUserValidator.validate(existingUsers, addUserRQ);

        // then
        // no exception
    }

    private static AddUserRQ addUserRQ(String email, String nickname) {
        AddUserRQ addUserRQ = new AddUserRQ();
        addUserRQ.setEmail(email);
        addUserRQ.setNickname(nickname);
        return addUserRQ;
    }

    private static User user(String email, String nickname) {
        User user = new User();
        user.setEmail(email);
        user.setNickname(nickname);
        return user;
    }
}