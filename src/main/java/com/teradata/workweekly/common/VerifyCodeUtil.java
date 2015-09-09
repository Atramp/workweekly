package com.teradata.workweekly.common;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by alex on 15/8/12.
 */
public class VerifyCodeUtil {

    public static String generate(String id) {
        return generate(id, 4);
    }

    public static String generate(String id, int length) {
        String code = RandomStringUtils.randomNumeric(length);
        SMSSessionManager.addSession(id, code);
        return code;
    }

    public static boolean verify(String id, String code) {
        return SMSSessionManager.verify(id, code);
    }

    public static boolean verify(String id, String code, long timeout) {
        return false;
    }
}
