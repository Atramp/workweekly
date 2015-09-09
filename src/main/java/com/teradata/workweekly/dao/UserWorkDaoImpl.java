package com.teradata.workweekly.dao;

import com.teradata.workweekly.bean.entity.UserWork;
import com.teradata.workweekly.dao.common.AbstractCommonDao;
import com.teradata.workweekly.dao.interfaces.UserWorkDao;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.ibatis.session.SqlSession;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by alex on 15/8/18.
 */
public class UserWorkDaoImpl extends AbstractCommonDao implements UserWorkDao {

    @Override
    public List<Map> getUserWorkList(String id, String startDate, String endDate) {
        try (SqlSession sqlSession = openSession()) {
            Param param = new Param();
            param.put("USER_ID", id);
            param.put("START_DATE", DateUtils.parseDate(startDate, "yyyyMMdd"));
            param.put("END_DATE", DateUtils.parseDate(endDate, "yyyyMMdd"));
            return sqlSession.selectList("UserWork.selectUserWorkByIDStartDateEndDate", param);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addUserWork(UserWork userWork) {
        try (SqlSession sqlSession = openSession()) {
            Param param = new Param();
            param.put("USER_ID", userWork.getUserID());
            param.put("REQUIREMENT_ID", userWork.getRequirementID());
            param.put("DATE", DateUtils.parseDate(userWork.getDate(), "yyyyMMdd"));
            param.put("DESCRIPTION", userWork.getDescription());
            param.put("HOURS", userWork.getHours());
            return sqlSession.insert("UserWork.addUserWork", param) == 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addUserWorks(List<UserWork> userWorkList) {
        try (SqlSession sqlSession = openSession(false)) {
            boolean result = true;
            for (UserWork userWork : userWorkList) {
                Param param = new Param();
                param.put("USER_ID", userWork.getUserID());
                param.put("REQUIREMENT_ID", userWork.getRequirementID());
                param.put("DATE", DateUtils.parseDate(userWork.getDate(), "yyyyMMdd"));
                param.put("DESCRIPTION", userWork.getDescription());
                param.put("HOURS", userWork.getHours());
                result &= (sqlSession.insert("UserWork.addUserWork", param) == 1);
            }
            if (result)
                sqlSession.commit();
            else
                sqlSession.rollback();
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUserWork(UserWork userWork) {
        try (SqlSession sqlSession = openSession()) {
            Param param = new Param();
            param.put("ID", userWork.getId());
            param.put("REQUIREMENT_ID", userWork.getRequirementID());
            param.put("DESCRIPTION", userWork.getDescription());
            param.put("HOURS", userWork.getHours());
            System.out.println("============" + userWork.getDescription());
            return sqlSession.insert("UserWork.updateUserWork", param) == 1;
        }
    }

    @Override
    public boolean deleteUserWork(String userWorkID) {
        try (SqlSession sqlSession = openSession()) {
            Param param = new Param();
            param.put("ID", userWorkID);
            return sqlSession.insert("UserWork.deleteUserWork", param) == 1;
        }
    }
}
