package com.teradata.workweekly.service.interfaces;

import com.teradata.workweekly.bean.entity.UserWork;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 15/7/22.
 */
public interface UserWorkService {

    UserWork getUserWork(String id);

    List<Map> getUserWorks(String userID, String month);

    List<Map> getUserWorks(String userID, String startDate, String endDate);

    List<Map> getUserWorks(String startDate, String endDate, int status);

    boolean addUserWork(UserWork userWork);

    boolean addUserWorks(List<UserWork> userWorkList);

    boolean updateUserWork(UserWork userWork);

    boolean deleteUserWork(String id);

    boolean disableEdit(String startDate, String endDate);


}
