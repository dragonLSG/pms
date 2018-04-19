package com.dragon.common;

import java.io.Serializable;

public class DataList implements Serializable {

    private static final long serialVersionUID = 8067895376499393047L;

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
