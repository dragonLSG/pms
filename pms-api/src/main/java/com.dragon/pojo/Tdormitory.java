package com.dragon.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class Tdormitory implements Serializable {
    private static final long serialVersionUID = -7369345441830300384L;
    private Integer dormitoryid;

    private String dormitoryname;

    private BigDecimal balance;

    public Integer getDormitoryid() {
        return dormitoryid;
    }

    public void setDormitoryid(Integer dormitoryid) {
        this.dormitoryid = dormitoryid;
    }

    public String getDormitoryname() {
        return dormitoryname;
    }

    public void setDormitoryname(String dormitoryname) {
        this.dormitoryname = dormitoryname == null ? null : dormitoryname.trim();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}