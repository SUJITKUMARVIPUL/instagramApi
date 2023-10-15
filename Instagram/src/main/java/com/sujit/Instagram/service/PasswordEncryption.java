package com.sujit.Instagram.service;

import jakarta.xml.bind.DatatypeConverter;
import org.apache.tomcat.util.security.MD5Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

public class PasswordEncryption {

    public static String encrypt(String newPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(newPassword.getBytes());
        byte[] digested =  md5.digest();
        return DatatypeConverter.printHexBinary(digested);

    }
}
