package com.teradata.workweekly.service.interfaces;

import com.teradata.workweekly.bean.entity.User;
import com.teradata.workweekly.bean.entity.UserPermission;

import java.util.List;

/**
 * Created by alex on 15/7/22.
 */
public interface UserService {
    List<User> getAllUsers();

    List<UserPermission> getAllUsersWithPermission();

    User getUserByID(String username);

    User getUserByPhone(String phone);

    UserPermission getUserAndPermissionByPhone(String phone);

    boolean saveUserWithPermission(UserPermission user);

    boolean deleteUser(String id);
}
