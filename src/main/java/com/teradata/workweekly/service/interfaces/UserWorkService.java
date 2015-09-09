package com.teradata.workweekly.service.interfaces;

import com.teradata.workweekly.bean.entity.UserWork;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 15/7/22.
 */
public interface UserWorkService {

    public UserWork getUserWork(String id);

    public List<Map> getUserWorks(String userID, String month);

    public List<Map> getUserWorks(String userID, String startDate, String endDate);

    public boolean addUserWork(UserWork userWork);

    public boolean addUserWorks(List<UserWork> userWorkList);

    public boolean updateUserWork(UserWork userWork);

    public boolean deleteUserWork(String id);

}
