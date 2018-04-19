package com.dragon.common.model;

import com.dragon.pojo.Tuser;

import java.io.Serializable;

public class User extends Tuser implements Serializable {

    private static final long serialVersionUID = 882361394595330530L;

    private Integer pageNum;
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
