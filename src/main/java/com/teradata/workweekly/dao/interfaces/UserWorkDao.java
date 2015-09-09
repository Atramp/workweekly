package com.teradata.workweekly.dao.interfaces;

import com.teradata.workweekly.bean.entity.UserWork;

import java.util.List;
import java.util.Map;

/**
 * Created by alex on 15/8/18.
 */
public interface UserWorkDao {

    List<Map> getUserWorkList(String id, String startDate, String endDate);

    boolean addUserWork(UserWork userWork);

    boolean addUserWorks(List<UserWork> userWorkList);

    boolean updateUserWork(UserWork userWork);

    boolean deleteUserWork(String userWorkID);

}
