package com.teradata.workweekly.controller;

import com.teradata.workweekly.bean.entity.User;
import com.teradata.workweekly.bean.entity.UserPermission;
import com.teradata.workweekly.bean.response.Response;
import com.teradata.workweekly.service.interfaces.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by alex on 15/7/22.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/list")
    public Response getAllUsers() throws Exception {
        List users = userService.getAllUsersWithPermission();
        if (users != null && !users.isEmpty())
            return new Response(Response.RESULT.SUCCESS, "获取数据成功", users);
        return new Response(Response.RESULT.FAIL, "获取数据失败");
    }

    @ResponseBody
    @RequestMapping(value = "/{id}")
    public Response getUserByID(@PathVariable String id) throws Exception {
        User user = userService.getUserByID(id);
        if (user != null)
            return new Response(Response.RESULT.SUCCESS, "获取数据成功", user);
        return new Response(Response.RESULT.FAIL, "获取数据失败");
    }

    @ResponseBody
    @RequestMapping(value = "/permission/{id}")
    public Response getUserWithPermissionByID(@PathVariable String id) throws Exception {
        UserPermission user = userService.getUserAndPermissionByPhone(id);
        if (user != null)
            return new Response(Response.RESULT.SUCCESS, "获取数据成功", user);
        return new Response(Response.RESULT.FAIL, "获取数据失败");
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public Response saveUser(String id, String name, String phone, String permission) throws Exception {
        UserPermission userPermission = new UserPermission();
        userPermission.setId(StringUtils.defaultString(id, phone));
        userPermission.setName(name);
        userPermission.setPhone(phone);
        userPermission.setPermission(permission);
        if (userService.saveUserWithPermission(userPermission))
            return new Response(Response.RESULT.SUCCESS, "保存数据成功");
        return new Response(Response.RESULT.FAIL, "保存数据失败");
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}")
    public Response deleteUser(@PathVariable String id) throws Exception {
        if (userService.deleteUser(id))
            return new Response(Response.RESULT.SUCCESS, "删除用户成功");
        return new Response(Response.RESULT.FAIL, "删除用户失败");
    }


}
