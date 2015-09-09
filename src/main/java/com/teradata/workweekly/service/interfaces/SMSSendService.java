package com.teradata.workweekly.service.interfaces;

/**
 * Created by alex on 15/8/25.
 */
public interface SMSSendService {
    void sendSMS(String mobile, String content);

    void sendVerifyCode(String mobile, String verifyCode);
}
