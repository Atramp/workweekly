package com.teradata.workweekly.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by alex on 15/7/22.
 */
@RestController
@RequestMapping("/user-work/")
public class UserWorkController {

    @RequestMapping("/list")
    @ResponseBody
    public List getUserWorks(String username, String start_date, String end_date) throws Exception {
        return null;
    }

    @RequestMapping("/list-by-date")
    @ResponseBody
    public List getUserWorks(String userID, String date) throws Exception {
        return null;
    }
}
