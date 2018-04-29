package com.dragon.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TmonUse implements Serializable {
    private static final long serialVersionUID = -2671972023252244922L;
    private Integer dormitoryid;

    private BigDecimal melectUse;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;

    private BigDecimal mfee;

    public Integer getDormitoryid() {
        return dormitoryid;
    }

    public void setDormitoryid(Integer dormitoryid) {
        this.dormitoryid = dormitoryid;
    }

    public BigDecimal getMelectUse() {
        return melectUse;
    }

    public void setMelectUse(BigDecimal delectUse) {
        this.melectUse = delectUse;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getMfee() {
        return mfee;
    }

    public void setMfee(BigDecimal mfee) {
        this.mfee = mfee;
    }
}