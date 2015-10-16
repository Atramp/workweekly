package com.teradata.workweekly.controller;

import com.teradata.workweekly.bean.entity.Requirement;
import com.teradata.workweekly.bean.response.Response;
import com.teradata.workweekly.service.interfaces.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
            return new Response(Response.RESULT.SUCCESS, "获取数据成功", requirementList);
        }
        return new Response(Response.RESULT.FAIL, "获取失败");
    }

    @ResponseBody
    @RequestMapping("/requires/v2")
    public Response getRequirementList2(String username) throws Exception {
        List<Requirement> requirementList = requirementService.getAllRequirement();
        if (requirementList != null) {
            return new Response(Response.RESULT.SUCCESS, "获取数据成功", requirementList);
        }
        return new Response(Response.RESULT.FAIL, "获取失败");
    }

    @ResponseBody
    @RequestMapping("/categories")
    public Response getAllCategories() throws Exception {
        List<Map> categories = requirementService.getAllCategories();
        if (categories != null) {
            return new Response(Response.RESULT.SUCCESS, "获取数据成功", categories);
        }
        return new Response(Response.RESULT.FAIL, "获取失败");
    }

    @ResponseBody
    @RequestMapping("/require/get")
    public Response getRequirement(String id) throws Exception {
        Map requirement = requirementService.getRequirementRaw(id);
        if (requirement != null && !requirement.isEmpty())
            return new Response(Response.RESULT.SUCCESS, "获取数据成功", requirement);
        return new Response(Response.RESULT.FAIL, "获取失败");
    }

    @ResponseBody
    @RequestMapping("/require/save")
    public Response saveRequirement(@ModelAttribute("requirement") Requirement requirement) throws Exception {
        if (!requirementService.updateRequirement(requirement))
            if (!requirementService.addRequirement(requirement))
                return new Response(Response.RESULT.FAIL, "保存失败");
        return new Response(Response.RESULT.SUCCESS, "保存成功");
    }

    @ResponseBody
    @RequestMapping("/require/toggle")
    public Response toggleRequirement(String id, String operate) throws Exception {
        Requirement requirement = new Requirement();
        requirement.setId(id);
        requirement.setStatus("deactive".equals(operate) ? "1" : "0");
        if (requirementService.updateRequirement(requirement))
            return new Response(Response.RESULT.SUCCESS, "操作成功");
        return new Response(Response.RESULT.FAIL, "操作失败");
    }

    @ResponseBody
    @RequestMapping("/require/field/all/options")
    public Response getAllFiledsOptions() throws Exception {
        Map options = requirementService.getAllOptions();
        return new Response(Response.RESULT.SUCCESS, "获取数据成功", options);
    }

    @ResponseBody
    @RequestMapping("/require/field/options/get")
    public Response getFieldOptions(String id) throws Exception {
        List options = requirementService.getOptionsByField(id);
        return new Response(Response.RESULT.SUCCESS, "获取数据成功", options);
    }

    @ResponseBody
    @RequestMapping("/require/field/options/add")
    public Response addFieldOption(String type, String name) throws Exception {
        String value = requirementService.addOption(type, name);
        if (value != null && !value.isEmpty() && !"-1".equals(value)) {
            Map result = new HashMap();
            result.put("name", name);
            result.put("value", value);
            return new Response(Response.RESULT.SUCCESS, "保存成功", result);
        }
        return new Response(Response.RESULT.FAIL, "保存失败");
    }

    @ResponseBody
    @RequestMapping("/require /field/options/delete")
    public Response deleteFieldOption(String type, String value) throws Exception {
        if (requirementService.deleteOption(type, value))
            return new Response(Response.RESULT.SUCCESS, "删除成功");
        return new Response(Response.RESULT.FAIL, "删除失败");
    }
}
