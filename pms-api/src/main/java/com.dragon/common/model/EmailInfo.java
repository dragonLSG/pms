package com.dragon.common.model;

public class EmailInfo {

    private String toEmail;
    private String title;
    private String msg;

    public EmailInfo() {
        super();
    }

    public EmailInfo(String toEmail, String title, String msg) {
        this.toEmail = toEmail;
        this.title = title;
        this.msg = msg;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
