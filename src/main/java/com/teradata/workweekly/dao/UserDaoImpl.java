package com.teradata.workweekly.dao;

import com.teradata.workweekly.bean.entity.User;
import com.teradata.workweekly.bean.entity.UserWork;
import com.teradata.workweekly.dao.common.AbstractCommonDao;
import com.teradata.workweekly.dao.interfaces.UserDao;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by alex on 15/7/22.
 */
public class UserDaoImpl extends AbstractCommonDao implements UserDao {

    public User getUserByPhone(String phone) {
        try (SqlSession session = openSession()) {
            Param param = new Param();
            param.put("PHONE", phone);
            return session.selectOne("User.selectUserByPhone", param);
        }
    }

    public User getUserByID(String id) {
        try (SqlSession session = openSession()) {
            Param param = new Param();
            param.put("ID", id);
            return session.selectOne("User.selectUserByID", param);
        }
    }

    public List<User> getAllUsers() {
        try (SqlSession session = openSession()) {
            return session.selectOne("User.selectAllUsers");
        }
    }
}
