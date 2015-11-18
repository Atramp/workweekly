package com.teradata.workweekly.dao.interfaces;

import com.teradata.workweekly.bean.entity.User;
import com.teradata.workweekly.bean.entity.UserPermission;

import java.util.List;
import java.util.Map;

/**
 * Created by alex on 15/7/22.
 */
public interface UserDao {

    User selectUserByPhone(String phone);

    User selectUserByID(String id);

    List<User> selectAllUsers();

    List<UserPermission> selectAllUsersWithPermission();

    UserPermission selectUserPermissionByID(String userID);

    boolean insertUser(User user);

    boolean insertUserPermission(UserPermission userPermission);

    boolean updateUser(User user);

    boolean deleteUserPermission(String userID);
}
