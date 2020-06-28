package com.application.data.util.hashing;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Hashtable;

public interface PasswordEncoder {
    Hashtable<String, String> hash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException;
    Hashtable<String, String> hash(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
