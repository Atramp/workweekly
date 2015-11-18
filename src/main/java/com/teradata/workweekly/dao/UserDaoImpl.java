package com.teradata.workweekly.dao;

import com.teradata.workweekly.bean.entity.User;
import com.teradata.workweekly.bean.entity.UserPermission;
import com.teradata.workweekly.bean.entity.UserWork;
import com.teradata.workweekly.dao.common.AbstractCommonDao;
import com.teradata.workweekly.dao.interfaces.UserDao;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

/**
 * Created by alex on 15/7/22.
 */
public class UserDaoImpl extends AbstractCommonDao implements UserDao {

    public User selectUserByPhone(String phone) {
        try (SqlSession session = openSession()) {
            Param param = new Param();
            param.put("PHONE", phone);
            return session.selectOne("User.selectUserByPhone", param);
        }
    }

    public User selectUserByID(String id) {
        try (SqlSession session = openSession()) {
            Param param = new Param();
            param.put("ID", id);
            return session.selectOne("User.selectUserByID", param);
        }
    }

    public List<User> selectAllUsers() {
        try (SqlSession session = openSession()) {
            return session.selectList("User.selectAllUsers");
        }
    }

    @Override
    public List<UserPermission> selectAllUsersWithPermission() {
        try (SqlSession session = openSession()) {
            return session.selectList("User.selectAllUsersWithPermission");
        }
    }

    public UserPermission selectUserPermissionByID(String userID) {
        try (SqlSession session = openSession()) {
            return session.selectOne("User.selectUserPermissionByID", new Param().put("ID", userID));
        }
    }

    @Override
    public boolean insertUser(User user) {
        try (SqlSession session = openSession()) {
            return session.insert("User.insertUser", new Param().put("USER", user)) == 1;
        }
    }

    @Override
    public boolean insertUserPermission(UserPermission userPermission) {
        try (SqlSession session = openSession()) {
            return session.insert("User.insertUserPermission", new Param().put("USER_PERMISSION", userPermission)) == 1;
        }
    }

    @Override
    public boolean updateUser(User user) {
        try (SqlSession session = openSession()) {
            return session.update("User.updateUser", new Param().put("USER", user)) == 1;
        }
    }


    @Override
    public boolean deleteUserPermission(String userID) {
        try (SqlSession session = openSession()) {
            session.delete("User.deleteUserPermission", new Param().put("USER_ID", userID));
            return true;
        }
    }
}
