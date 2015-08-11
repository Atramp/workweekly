package com.teradata.workweekly.bean.response;

import java.util.Map;

/**
 * Created by alex on 15/7/22.
 */
public class Response {
    protected String msg;
    protected String result;
    protected Map data;

    public Response() {
    }

    public Response(String msg, String result) {
        this.msg = msg;
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }
}
