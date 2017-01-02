package eu.needtocode.validation;

import eu.needtocode.exception.ValidationException;
import eu.needtocode.model.AddUserRQ;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static eu.needtocode.validation.UserValidator.USER_EMAIL_SHOULD_CONTAINS_SIGN;

public class UserValidatorTest {

    private UserValidator userValidator = new UserValidator();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldThrowExceptionWhenEmailDoesNotContainsAtChar() {
        // given
        AddUserRQ userRQ = new AddUserRQ();
        userRQ.setEmail("asd.asd.com");

        // expect
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage(USER_EMAIL_SHOULD_CONTAINS_SIGN);

        // when
        userValidator.validate(userRQ);
    }

    @Test
    public void shouldNotThrowAnyExceptionForValidEmailAddress() {
        // given
        AddUserRQ userRQ = new AddUserRQ();
        userRQ.setEmail("asd@asd.com");

        // when
        userValidator.validate(userRQ);

        // then
        // no exception
    }
}