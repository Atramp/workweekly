package com.teradata.workweekly.service;

import com.teradata.workweekly.bean.entity.User;
import com.teradata.workweekly.dao.UserDaoImpl;
import com.teradata.workweekly.dao.interfaces.UserDao;
import com.teradata.workweekly.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by alex on 15/8/12.
 */
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUserByID(String ID) {
        return userDao.getUserByID(ID);
    }

    public User getUserByPhone(String phone) {
        return userDao.getUserByPhone(phone);
    }

}
