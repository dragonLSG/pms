package com.dragon.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ThourUse implements Serializable {
    private static final long serialVersionUID = 5502499741266210947L;
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