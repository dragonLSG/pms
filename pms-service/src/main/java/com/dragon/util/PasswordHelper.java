package com.dragon.util;


import org.apache.shiro.crypto.hash.SimpleHash;

public class PasswordHelper {
    private static String algorithmName = "md5";
    private static Integer hashIterations = 2;

    public static String encryptPassword(String account, String password) {
        String newPassword = new SimpleHash(algorithmName, password, account, hashIterations).toHex();
        return newPassword;
    }
}
