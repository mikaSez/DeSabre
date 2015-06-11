package info.desabre.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by MikaSez on 11/06/2015.
 */
public class PasswordEncrypt {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static PasswordEncrypt instance = new PasswordEncrypt();

    public static PasswordEncrypt getInstance() {
        return instance;
    }

    public String encrypt(String str) {
        return passwordEncoder.encode(str);
    }
}

