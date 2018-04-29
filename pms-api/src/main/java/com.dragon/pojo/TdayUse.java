package com.dragon.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TdayUse implements Serializable {
    private static final long serialVersionUID = -7344099408518970123L;
    private Integer dormitoryid;

    private BigDecimal delectUse;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;

    private BigDecimal dfee;

    public Integer getDormitoryid() {
        return dormitoryid;
    }

    public void setDormitoryid(Integer dormitoryid) {
        this.dormitoryid = dormitoryid;
    }

    public BigDecimal getDelectUse() {
        return delectUse;
    }

    public void setDelectUse(BigDecimal delectUse) {
        this.delectUse = delectUse;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getDfee() {
        return dfee;
    }

    public void setDfee(BigDecimal dfee) {
        this.dfee = dfee;
    }
}