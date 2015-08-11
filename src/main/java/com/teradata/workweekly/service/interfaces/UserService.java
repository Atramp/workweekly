package com.teradata.workweekly.service.interfaces;

import com.teradata.workweekly.bean.entity.User;

/**
 * Created by alex on 15/7/22.
 */
public interface UserService {
    public User getUserByUsername(String username);

    public User getUserByPhone(String phone);

}
