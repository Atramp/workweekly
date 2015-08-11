package com.teradata.workweekly.controller;

import com.teradata.workweekly.bean.response.Response;
import com.teradata.workweekly.common.SMSSessionManager;
import com.teradata.workweekly.service.interfaces.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 15/7/22.
 */
@RestController
public class UserController {
    private UserService service;

    @ResponseBody
    @RequestMapping("/verify-code/get")
    public Response getVarifyCode(String phone) {
        //查询用户是否存在
        boolean exists = service.getUserByPhone(phone) != null;
        if (exists) {
            String code = RandomStringUtils.randomNumeric(4);
            Map data = new HashMap();
            data.put("code", code);
            SMSSessionManager.addSession(phone, code);
            return new Response(0, "用户存在", data);
        } else {
            return new Response(1, "用户不存在");
        }
    }

    @ResponseBody
    @RequestMapping("/verify-code/verify")
    public Response varify(String phone, String code) {
        Map data = new HashMap();
        Response response = new Response(data);
        return response;
    }

    @ResponseBody
    @RequestMapping("/password/reset")
    public Response resetPassword(String username, String phone, String password) {
        Map data = new HashMap();
        Response response = new Response(data);
        return response;
    }

}
