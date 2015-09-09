package com.teradata.workweekly.dao.interfaces;

import com.teradata.workweekly.bean.entity.User;

/**
 * Created by alex on 15/7/22.
 */
public interface UserDao {

    User getUserByPhone(String phone);

    User getUserByID(String id);

}
