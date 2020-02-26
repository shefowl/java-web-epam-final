package by.epam.taxibuber.service.util;

import by.epam.buber.service.impl.util.UserValidator;
import org.junit.Assert;
import org.junit.Test;

public class UserValidatorTest {
    private UserValidator validator = new UserValidator();

    @Test
    public void isValidPasswordTestTrue(){
        String password = "test";
        Assert.assertTrue(validator.isValidPassword(password));
    }

    @Test
    public void isValidPasswordTestFalse(){
        String password = "tst";
        Assert.assertFalse(validator.isValidPassword(password));
    }

    @Test
    public void isValidEmailTestTrue(){
        String email = "strelok4855@gmail.com";
        Assert.assertTrue(validator.isValidEmail(email));
    }

    @Test
    public void isValidEmailTestFalse(){
        String email = "strelok@gmail.";
        Assert.assertFalse(validator.isValidEmail(email));
    }

    @Test
    public void isValidPhoneTestTrue(){
        String phone = "375447732560";
        Assert.assertTrue(validator.isValidPhone(phone));
    }

    @Test
    public void isValidPhoneTestFalse(){
        String phone = "375(44)773-25-60";
        Assert.assertFalse(validator.isValidPhone(phone));
    }
}
