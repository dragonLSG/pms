package com.dragon.common;

public class DataList {

    private Object data;
    private PageInf pageInf;

    public DataList(Object data, PageInf pageInf) {
        super();
        this.data = data;
        this.pageInf = pageInf;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public PageInf getPageInf() {
        return pageInf;
    }

    public void setPageInf(PageInf pageInf) {
        this.pageInf = pageInf;
    }


}
