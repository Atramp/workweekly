package com.teradata.workweekly.common;


import com.teradata.workweekly.common.config.Configuration;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by alex on 15/4/28.
 */
public class SMSSessionManager {
    private static class SMSSession {
        private String phone;   //用户手机号码
        private String code;     //用户验证码
        private Date expireTime;  //结束时间

        private SMSSession(String phone, String code) {
            this.phone = phone;
            this.code = code;
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MILLISECOND, Configuration.getInt("SMS_EXPIRED_TIME", 1200000));
            this.expireTime = calendar.getTime();
        }

        private SMSSession(String phone, String code, int milliSecond) {
            this.phone = phone;
            this.code = code;
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MILLISECOND, milliSecond);
            this.expireTime = calendar.getTime();
        }

        private String getCode() {
            return code;
        }

        private boolean isTimeout() {
            return new Date().compareTo(this.expireTime) == 1;
        }

    }

    private static final ConcurrentHashMap<String, SMSSession> sessionMap = new ConcurrentHashMap<String, SMSSession>();

    public static boolean validate(String key, String code) {
        SMSSession smsSession = sessionMap.get(key);
        return smsSession != null && code.equals(smsSession.getCode()) && smsSession.isTimeout();
    }

    public static void addSession(String phone, String code) {
        sessionMap.put(phone, new SMSSession(phone, code));
    }

    public static void addSession(String phone, String code, int milliSecond) {
        sessionMap.put(phone, new SMSSession(phone, code, milliSecond));
    }

    public static void addSession(String username, String phone, String code) {
        sessionMap.put(username, new SMSSession(phone, code));
    }

    public static void addSession(String username, String phone, String code, int milliSecond) {
        sessionMap.put(username, new SMSSession(phone, code, milliSecond));
    }

    public static void removeSession(String key) {
        sessionMap.remove(key);
    }

}
