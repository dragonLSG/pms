package com.dragon.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Tfeedback {
    private Integer fid;

    private String title;

    private String content;

    private String sender;

    private Date fdate;

    private Short fstatus;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }

    public Date getFdate() {
        return fdate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public void setFdate(Date fdate) {
        this.fdate = fdate;
    }

    public Short getFstatus() {
        return fstatus;
    }

    public void setFstatus(Short fstatus) {
        this.fstatus = fstatus;
    }
}