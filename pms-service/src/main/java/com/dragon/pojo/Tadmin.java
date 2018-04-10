package com.dragon.pojo;

public class Tadmin {
    private String account;

    private String adminname;

    private String passwd;

    private Integer authority;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname == null ? null : adminname.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }
}