package com.dragon.shiro.util;


import com.dragon.common.model.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {
    private String algorithmName = "md5";
    private int hashIterations = 2;

    public void encryptPassword(User user) {
        String newPassword = new SimpleHash(algorithmName, user.getPasswd(), ByteSource.Util.bytes(user.getAccount()), hashIterations).toHex();
        user.setPasswd(newPassword);
    }

    public static void main(String[] args) {
        PasswordHelper passwordHelper = new PasswordHelper();
        User user = new User();
        user.setAccount("3114006432");
        user.setPasswd("123456");
        passwordHelper.encryptPassword(user);
        System.out.println(user.getPasswd());
    }
}
