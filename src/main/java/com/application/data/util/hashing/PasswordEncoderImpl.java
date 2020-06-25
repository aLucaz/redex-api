package com.application.data.util.hashing;

import com.application.shared.Constant;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Hashtable;

@Component
public class PasswordEncoderImpl implements PasswordEncoder {

    @Override
    public Hashtable<String, String> hash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Hashtable<String, String> hashtable = new Hashtable<>();
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = secureRandom.generateSeed(Constant.SALT_LENGTH);
        String passwordSalt = new String(salt);
        // hashing proccess
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), passwordSalt.getBytes(), Constant.PBEKEY_ITERATION_NUMBER, Constant.PBEKEY_LENGTH);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(Constant.HASH_ALGORITHM);
        byte[] hash = secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
        String passwordHash = Base64.getMimeEncoder().encodeToString(hash);
        // adding values to return
        hashtable.put("passwordHash", passwordHash);
        hashtable.put("passwordSalt", passwordSalt);
        return hashtable;
    }

    @Override
    public Hashtable<String, String> hash(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Hashtable<String, String> hashtable = new Hashtable<>();
        // hashing proccess
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), Constant.PBEKEY_ITERATION_NUMBER, Constant.PBEKEY_LENGTH);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(Constant.HASH_ALGORITHM);
        byte[] hash = secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
        String passwordHash = Base64.getMimeEncoder().encodeToString(hash);
        String passwordSalt = salt;
        // adding values to return
        hashtable.put("passwordHash", passwordHash);
        hashtable.put("passwordSalt", passwordSalt);
        return hashtable;
    }
}
