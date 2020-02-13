package by.epam.buber.service.impl.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {
    private final String gensalt;

    public PasswordEncoder() {
        gensalt = BCrypt.gensalt();
    }

    public String encode(String password) {
        return BCrypt.hashpw(password, gensalt);
    }

    public boolean checkPassword(String password, String hashPassword) {
        return BCrypt.checkpw(password, hashPassword);
    }
}
