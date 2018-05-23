package com.dragon.common.model;

import com.dragon.common.PageSizeAndNum;

import java.io.Serializable;

public class Notice extends PageSizeAndNum implements Serializable {

    private static final long serialVersionUID = 3269444664964600058L;

    private String title;
    private String publisher;
    private String startDate;
    private String endDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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
}
