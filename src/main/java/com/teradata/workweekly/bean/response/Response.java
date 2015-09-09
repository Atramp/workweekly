package com.teradata.workweekly.bean.response;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 15/7/22.
 */
public class Response {
    public enum RESULT {
        ERROR(-1),
        SUCCESS(0),
        FAIL(1);
        private int result;

        RESULT(int i) {
            result = i;
        }
    }

    protected int result;
    protected String msg;
    protected Object data;

    private Response() {

    }

    public Response(RESULT result, String msg) {
        this.result = result.result;
        this.msg = msg;
        this.data = new HashMap(0);
    }

    public Response(RESULT result, String msg, Map data) {
        this.result = result.result;
        this.msg = msg;
        if(data == null)
            data = new HashMap(0);
        this.data = data;
    }

    public Response(RESULT result, String msg, List data) {
        this.result = result.result;
        this.msg = msg;
        if(data == null)
            data = new ArrayList(0);
        this.data = data;
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

    public void setResult(RESULT result) {
        this.result = result.result;
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


    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
