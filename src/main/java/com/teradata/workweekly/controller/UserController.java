package com.teradata.workweekly.controller;

import com.teradata.workweekly.bean.entity.User;
import com.teradata.workweekly.bean.response.Response;
import com.teradata.workweekly.common.VerifyCodeUtil;
import com.teradata.workweekly.service.interfaces.SMSSendService;
import com.teradata.workweekly.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 15/7/22.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SMSSendService smsSendService;

    @ResponseBody
    @RequestMapping(value = "/verifycode", params = "method=generate")
    public Response getVerifyCode(String phone) throws Exception {
        try {
            if (userService.getUserByPhone(phone) != null) {
                String code = VerifyCodeUtil.generate(phone);
                smsSendService.sendVerifyCode(phone, code);
                Map data = new HashMap();
                data.put("code", code);
                return new Response(Response.RESULT.SUCCESS, "获取成功", data);
            } else {
                return new Response(Response.RESULT.FAIL, "用户不存在");
            }
        } catch (Exception e) {
            return new Response(Response.RESULT.FAIL, "获取失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/verifycode", params = "method=verify")
    public Response verifyCode(String phone, String code) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (VerifyCodeUtil.verify(phone, code)) {
                User user = userService.getUserByPhone(phone);
                request.getSession().setAttribute("USER", user);
                Map data = new HashMap();
                data.put("name", user.getName());
                data.put("permission", phone.equals("15101004021") ? 2 : phone.equals("15120047973") ? 4 : 1);
                return new Response(Response.RESULT.SUCCESS, "验证成功", data);
            }
            return new Response(Response.RESULT.FAIL, "验证失败");
        } catch (Exception e) {
            return new Response(Response.RESULT.FAIL, "获取失败");
        }
    }


}
