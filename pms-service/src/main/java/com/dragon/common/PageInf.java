package com.dragon.common;

import com.github.pagehelper.PageInfo;

public class PageInf {
    private long pageNum;
    private long pageSize;
    private long totalNum; //总记录数
    private long totalPageNum;    //总页数

    public PageInf(PageInfo pageInfo) {
        super();
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.totalNum = pageInfo.getTotal();
        this.totalPageNum = (long) Math.ceil((double) this.totalNum / this.pageSize);
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
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
