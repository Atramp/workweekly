package com.teradata.workweekly.service;

import com.teradata.workweekly.bean.entity.User;
import com.teradata.workweekly.dao.UserDaoImpl;
import com.teradata.workweekly.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by alex on 15/8/12.
 */
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDaoImpl userDao;

    public User getUserByID(String ID) {
        return userDao.getUserByID(ID);
    }

    public User getUserByPhone(String phone) {
        return userDao.getUserByPhone(phone);
    }

}
