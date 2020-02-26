package by.epam.buber.service.impl.util;

import java.util.regex.Pattern;

public class UserValidator {
    public static final int MIN_PASSWORD_LENGTH = 4;
    public static final String regexEmail = "^[A-Za-z0-9+_.-]+@.{2,}\\..{2,}$";
    public static final String regexPhone = "\\d{12}";

    public boolean isValidUser(String password, String email, String phone){
        return isValidPassword(password) && isValidEmail(email) && isValidPhone(phone);
    }

    public boolean isValidPassword(String password){
        return password.length() >= MIN_PASSWORD_LENGTH;
    }

    public boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile(regexEmail);
        return pattern.matcher(email).matches();
    }

    public boolean isValidPhone(String phone){
        Pattern pattern = Pattern.compile(regexPhone);
        return pattern.matcher(phone).matches();
    }
}
