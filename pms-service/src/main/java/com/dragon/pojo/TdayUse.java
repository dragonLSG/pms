package com.dragon.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class TdayUse {
    private Integer dormitoryid;

    private BigDecimal delectUse;

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