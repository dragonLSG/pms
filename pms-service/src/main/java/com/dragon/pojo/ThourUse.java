package com.dragon.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class ThourUse {
    private Integer dormitoryid;

    private BigDecimal helectUse;

    private Date date;

    public Integer getDormitoryid() {
        return dormitoryid;
    }

    public void setDormitoryid(Integer dormitoryid) {
        this.dormitoryid = dormitoryid;
    }

    public BigDecimal getHelectUse() {
        return helectUse;
    }

    public void setHelectUse(BigDecimal helectUse) {
        this.helectUse = helectUse;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}