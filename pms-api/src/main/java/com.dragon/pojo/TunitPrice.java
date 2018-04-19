package com.dragon.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TunitPrice implements Serializable {
    private static final long serialVersionUID = 530056735328611010L;
    private Integer priceid;

    private BigDecimal price;

    private Date date;

    private String operator;

    public Integer getPriceid() {
        return priceid;
    }

    public void setPriceid(Integer priceid) {
        this.priceid = priceid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }
}