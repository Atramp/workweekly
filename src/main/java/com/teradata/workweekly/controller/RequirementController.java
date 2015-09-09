package com.teradata.workweekly.controller;

import com.teradata.workweekly.bean.response.Response;
import com.teradata.workweekly.service.interfaces.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by alex on 15/8/19.
 */
@RestController
public class RequirementController {
    @Autowired
    private RequirementService requirementService;

    @ResponseBody
    @RequestMapping("/requires")
    public Response getRequirementList(String username) throws Exception {
        List<Map> requirementList = requirementService.getAllSimpleByUser(username);
        if (requirementList != null) {
            return new Response(Response.RESULT.SUCCESS, "成功获取数据", requirementList);
        }
        return new Response(Response.RESULT.FAIL, "获取失败");
    }

    @ResponseBody
    @RequestMapping("/categories")
    public Response getAllCategories() throws Exception {
        List<Map> categories = requirementService.getAllCategories();
        if (categories != null) {
            return new Response(Response.RESULT.SUCCESS, "成功获取数据", categories);
        }
        return new Response(Response.RESULT.FAIL, "获取失败");
    }
}
