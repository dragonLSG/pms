package com.dragon.pojo;

import java.io.Serializable;
import java.util.List;

public class Tuserpermission implements Serializable {

    private static final long serialVersionUID = 6873596458064783574L;

    private String account;

    private String username;

    private String passwd;

    private List<String> permissions;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}