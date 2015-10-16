package com.teradata.workweekly.service.interfaces;

import com.teradata.workweekly.bean.entity.User;

import java.util.List;

/**
 * Created by alex on 15/7/22.
 */
public interface UserService {
    List<User> getAllUsers();

    User getUserByID(String username);

    User getUserByPhone(String phone);
}
