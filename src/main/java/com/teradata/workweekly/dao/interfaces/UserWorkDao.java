package com.teradata.workweekly.dao.interfaces;

import com.teradata.workweekly.bean.entity.UserWork;

import java.util.List;
import java.util.Map;

/**
 * Created by alex on 15/8/18.
 */
public interface UserWorkDao {

    UserWork selectUserWork(String userID);

    List<Map> selectUserWorkList(String userID, String startDate, String endDate);

    boolean insertUserWork(UserWork userWork);

    boolean insertUserWorks(List<UserWork> userWorkList);

    boolean updateUserWork(UserWork userWork);

    boolean deleteUserWork(String userWorkID);

    List<Map> selectByStartEndDateStatus(String startDate, String endDate, int status);

    boolean updateStatusByStartEndDate(String startDate, String endDate, int status);

}
