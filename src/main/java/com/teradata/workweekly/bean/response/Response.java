package com.teradata.workweekly.bean.response;

import java.util.List;
import java.util.Map;

/**
 * Created by alex on 15/7/22.
 */
public class Response {
    protected int result;
    protected String msg;
    protected Object data;

    public Response() {

    }

    public Response(Map data) {
        this.data = data;
    }public Response(List data) {
        this.data = data;
    }

    public Response(int result, String msg) {
        this.msg = msg;
        this.result = result;
    }

    public Response(int result, String msg, Map data) {
        this(data);
        this.msg = msg;
        this.result = result;
    }

    public Response(int result, String msg, List data) {
        this(data);
        this.msg = msg;
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
