package com.teradata.workweekly.controller;

import com.teradata.workweekly.bean.response.Response;
import com.teradata.workweekly.service.interfaces.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(value = "/notification")
    @ResponseBody
    public Response sendNotification(String users) {
        if (statisticsService.sendNotification(users))
            return new Response(Response.RESULT.SUCCESS, "发送通知成功");
        return new Response(Response.RESULT.FAIL, "发送通知失败");
    }

    @RequestMapping(value = "/timesheet")
    @ResponseBody
    public Response collectAllUserWork(String start_date, String end_date) {
        List<Map> data = statisticsService.simplyStatisticsByStartEndDate(start_date, end_date);
        return new Response(Response.RESULT.SUCCESS, "获取数据成功", data);
    }

    @RequestMapping(value = "/statistics/excel/check")
    @ResponseBody
    public Response checkExcelStatus(String start_date, String end_date) {
        if (statisticsService.checkExcelStatus(start_date, end_date))
            return new Response(Response.RESULT.SUCCESS, "周报状态（已生成）");
        return new Response(Response.RESULT.FAIL, "周报状态（未生成）");
    }

    @RequestMapping(value = "/statistics/excel/generate")
    @ResponseBody
    public Response generateExcel(String start_date, String end_date) {
        if (statisticsService.generateExcel(start_date, end_date))
            return new Response(Response.RESULT.SUCCESS, "周报生成成功");
        return new Response(Response.RESULT.FAIL, "周报生成失败");
    }

}
