package com.teradata.workweekly.controller;

import com.teradata.workweekly.bean.response.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 15/9/24.
 */
@RestController
public class StatisticsController {
    @RequestMapping(value = "/notification")
    @ResponseBody
    public Response sendNotification(String users) {
        return new Response(Response.RESULT.SUCCESS, "获取数据成功");
    }

    @RequestMapping(value = "/timesheet")
    @ResponseBody
    public Response collectAllUserWork(String start_date, String end_date) {
        List<Map> data = new ArrayList<>();
        Map temp = new HashMap();
        temp.put("username", "15101004021");
        temp.put("name", "刘月腾");
        temp.put("hour", 40);
        data.add(temp);
        Map temp2 = new HashMap();
        temp2.put("username", "15120047973");
        temp2.put("name", "经怀飞");
        temp2.put("hour", 35);
        data.add(temp2);
        Map temp3 = new HashMap();
        temp3.put("username", "13901003498");
        temp3.put("name", "大祥哥");
        temp3.put("hour", 28);
        data.add(temp3);
        return new Response(Response.RESULT.SUCCESS, "获取数据成功", data);
    }
}
