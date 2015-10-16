package com.teradata.workweekly.dao.interfaces;

import com.teradata.workweekly.bean.entity.User;

import java.util.List;

/**
 * Created by alex on 15/7/22.
 */
public interface UserDao {

    User getUserByPhone(String phone);

    User getUserByID(String id);

    List<User> getAllUsers();
}
