package com.teradata.workweekly.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teradata.workweekly.bean.response.Response;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

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
        key.setTime(new Date());
        keys.put(user, key);
        return new Response(Response.RESULT.SUCCESS, "保存口令成功");
    }

    @RequestMapping(value = "/portal-key/get")
    @ResponseBody
    public Response getKey() {
        List<Key> keyList = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -10);
        Date indate = calendar.getTime();
        for (Map.Entry entry : (Set<Map.Entry>) keys.entrySet()) {
            Key key = (Key) entry.getValue();
            if (indate.before(key.getTime()))
                keyList.add(key);
        }
        return new Response(Response.RESULT.SUCCESS, "获取口令成功", keyList);
    }

    private class Key {
        String user;
        String code;
        Date time;

        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Shanghai")
        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }


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

    }

}
