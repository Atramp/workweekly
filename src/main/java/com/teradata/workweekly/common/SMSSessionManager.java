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
        private String id;   //用户手机号码
        private String code;     //用户验证码
        private Date expireTime;  //结束时间

        private SMSSession(String id, String code) {
            this(id, code, Configuration.getInt("SMS_EXPIRED_TIME", 1200000));
        }

        private SMSSession(String id, String code, int milliSecond) {
            this.id = id;
            this.code = code;
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MILLISECOND, milliSecond);
            this.expireTime = calendar.getTime();
        }

        private String getCode() {
            return code;
        }

        private boolean isTimeout() {
            return new Date().compareTo(this.expireTime) == -1;
        }

    }

    private static final ConcurrentHashMap<String, SMSSession> sessionMap = new ConcurrentHashMap<String, SMSSession>();

    public static boolean verify(String key, String code) {
        SMSSession smsSession = sessionMap.get(key);
        return smsSession != null && code.equals(smsSession.getCode()) && smsSession.isTimeout();
    }

    public static void addSession(String phone, String code) {
        sessionMap.put(phone, new SMSSession(phone, code));
    }

    public static void removeSession(String key) {
        sessionMap.remove(key);
    }

}
