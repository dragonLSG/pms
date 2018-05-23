package com.dragon.common.model;

import com.dragon.common.PageSizeAndNum;

import java.io.Serializable;

public class FeedBack extends PageSizeAndNum implements Serializable {

    private static final long serialVersionUID = 3839764126206189353L;

    private String title;
    private String startDate;
    private String endDate;
    private String sender;
    private Short status;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}
