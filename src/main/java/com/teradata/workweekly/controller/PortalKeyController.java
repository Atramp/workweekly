package com.teradata.workweekly.controller;

import com.teradata.workweekly.bean.response.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 15/10/16.
 */
@RestController
public class PortalKeyController {
    private final static Map keys = new HashMap<String, String>();

    static {
        Collections.synchronizedMap(keys);
    }

    @RequestMapping(value = "/portal-key/reset")
    @ResponseBody
    public Response reset() {
        keys.clear();
        return new Response(Response.RESULT.SUCCESS, "重置成功");
    }

    @RequestMapping(value = "/portal-key/save")
    @ResponseBody
    public Response saveKey(String user, String code) {
        Key key = new Key();
        key.setUser(user);
        key.setCode(code);
        key.setTime(new SimpleDateFormat("yyy-MM-dd hh:mm:ss").format(new Date()));
        keys.put(user, key);
        return new Response(Response.RESULT.SUCCESS, "保存口令成功");
    }

    @RequestMapping(value = "/portal-key/get")
    @ResponseBody
    public Response getKey() {
        return new Response(Response.RESULT.SUCCESS, "获取口令成功", keys);
    }

    private class Key {
        String user;
        String code;
        String time;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

}
