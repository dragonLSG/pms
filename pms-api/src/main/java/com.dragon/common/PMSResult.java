package com.dragon.common;

import java.io.Serializable;

public class PMSResult implements Serializable {

    private static final long serialVersionUID = 5618288548163332476L;

    private String status;
    private Object data;
    private String massage;

    private static volatile PMSResult singleton;

    private PMSResult() {
    }

    public static PMSResult getInstance(String status, Object data, String msg) {
        if (singleton == null) {
            synchronized (PMSResult.class) {
                if (singleton == null) {
                    singleton = new PMSResult();
                }
            }
        }
        synchronized (PMSResult.class) {
            singleton.setData(data);
            singleton.setMassage(msg);
            singleton.setStatus(status);
            return singleton;
        }
    }

    /**
     * @param data 数据
     * @param msg  信息
     * @return
     * @author Kim
     */
    public static PMSResult Ok(Object data, String msg) {

        return getInstance("200", data, msg);
    }

    public static PMSResult Ok() {

        return getInstance("200", null, "成功");
    }

    public static PMSResult Fail(String msg) {

        return getInstance("500", null, msg);
    }

    public static PMSResult Fail() {

        return getInstance("500", null, "错误");
    }

    /* setter and getter */
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

}
