package com.dragon.common;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;

public class PageInf extends PageSizeAndNum implements Serializable {
    private static final long serialVersionUID = 3974397181187306593L;

    private long totalNum; //总记录数
    private long totalPageNum;    //总页数

    public PageInf() {
    }

    public PageInf(PageInfo pageInfo) {
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.totalNum = pageInfo.getTotal();
        this.totalPageNum = (long) Math.ceil((double) this.totalNum / this.pageSize);
    }

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    public long getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(long totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

}
