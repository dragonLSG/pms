package com.dragon.common.model;


import com.dragon.common.PageSizeAndNum;

import java.io.Serializable;

public class Payment extends PageSizeAndNum implements Serializable {
    private static final long serialVersionUID = -3894038909044983893L;

    private String startDate;
    private String endDate;
    private Short status;

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
