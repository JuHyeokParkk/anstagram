package com.project.anstagram.authentication;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {

    public static String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
