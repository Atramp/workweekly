package com.teradata.workweekly.controller;

import com.teradata.workweekly.bean.entity.UserWork;
import com.teradata.workweekly.bean.response.Response;
import com.teradata.workweekly.service.interfaces.UserWorkService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 15/7/22.
 */
@RestController
@RequestMapping("/user/work")
public class UserWorkController {
    @Autowired
    private UserWorkService userWorkService;

    @RequestMapping("/list")
    @ResponseBody
    public Response getUserWorks(String username, String start_date, String end_date) throws Exception {
        try {
            List list = userWorkService.getUserWorks(username, start_date, end_date);
            return new Response(Response.RESULT.SUCCESS, "成功获取数据", list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(Response.RESULT.ERROR, "系统异常");
        }
    }

    @RequestMapping(value = "/list/monthly")
    @ResponseBody
    public Response getUserWorks(String username, String month) throws Exception {
        try {
            List list = userWorkService.getUserWorks(username, month);
            return new Response(Response.RESULT.SUCCESS, "成功获取数据", list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(Response.RESULT.ERROR, "系统异常");
        }
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public Response addUserWork(String username, String require, String desc, String hours, String date) {
        try {
            String _date[] = date.split(",");
            if (!ArrayUtils.isEmpty(_date)) {
                List<UserWork> userWorkList = new ArrayList<UserWork>();
                for (int i = 0; i < _date.length; i++) {
                    UserWork userWork = new UserWork();
                    userWork.setUserID(username);
                    userWork.setRequirementID(require);
                    userWork.setDate(_date[i]);
                    userWork.setDescription(desc);
                    userWork.setHours(hours);
                    userWorkList.add(userWork);
                }
                if (userWorkService.addUserWorks(userWorkList))
                    return new Response(Response.RESULT.SUCCESS, "保存成功");
            }
            return new Response(Response.RESULT.FAIL, "保存失败");
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(Response.RESULT.ERROR, "系统异常");
        }
    }

    @RequestMapping(value = "/edit")
    @ResponseBody
    public Response updateUserWork(String id, String require, String desc, String hours) {
        try {
            UserWork userWork = new UserWork();
            userWork.setId(id);
            userWork.setRequirementID(require);
            userWork.setDescription(desc);
            userWork.setHours(hours);
            if (userWorkService.updateUserWork(userWork))
                return new Response(Response.RESULT.SUCCESS, "保存成功");
            return new Response(Response.RESULT.FAIL, "保存失败");
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(Response.RESULT.ERROR, "系统异常");
        }
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Response deleteUserWork(String id) {
        try {
            if (userWorkService.deleteUserWork(id))
                return new Response(Response.RESULT.SUCCESS, "删除成功");
            return new Response(Response.RESULT.FAIL, "删除失败");
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(Response.RESULT.ERROR, "系统异常");
        }
    }

}
