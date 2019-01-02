package com.kiss.captcha.utils;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import static org.apache.commons.codec.digest.MessageDigestAlgorithms.MD5;

public class CryptUtil {

    static public String md5(String source) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(MD5);
        } catch (NoSuchAlgorithmException ignored) {
        }
        md.update(source.getBytes());
        return new BigInteger(1, md.digest()).toString(16);
    }

    static public String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
