package com.teradata.workweekly.controller;

import com.teradata.workweekly.bean.entity.UserPermission;
import com.teradata.workweekly.bean.response.Response;
import com.teradata.workweekly.common.VerifyCodeUtil;
import com.teradata.workweekly.service.interfaces.SMSSendService;
import com.teradata.workweekly.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 15/7/22.
 */
@RestController
public class LoginController {

    @ResponseBody
    @RequestMapping(value = "/manager/login")
    public Response getVerifyCode(String username, String password) throws Exception {
        if ("tdcmcc".equals(username) && "tdcmcc123".equals(password))
            return new Response(Response.RESULT.SUCCESS, "登录成功");
        return new Response(Response.RESULT.FAIL, "登录失败");
    }
}
