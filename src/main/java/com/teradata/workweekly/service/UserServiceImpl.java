package com.teradata.workweekly.service;

import com.teradata.workweekly.bean.entity.User;
import com.teradata.workweekly.bean.entity.UserPermission;
import com.teradata.workweekly.dao.interfaces.UserDao;
import com.teradata.workweekly.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;

import java.util.List;

/**
 * Created by alex on 15/8/12.
 */
public class UserServiceImpl extends AbstractTransactionService implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.selectAllUsers();
    }

    @Override
    public List<UserPermission> getAllUsersWithPermission() {
        return userDao.selectAllUsersWithPermission();
    }

    public User getUserByID(String ID) {
        return userDao.selectUserByID(ID);
    }

    public User getUserByPhone(String phone) {
        return userDao.selectUserByPhone(phone);
    }

    public UserPermission getUserAndPermissionByPhone(String phone) {
        return userDao.selectUserPermissionByID(phone);
    }

    @Override
    public boolean saveUserWithPermission(UserPermission user) {
        boolean result = false;
        TransactionStatus status = beginTransaction();
        try {
            // 1.保存用户表,更新不到则新增
            result = userDao.updateUser(user);
            if (!result)
                result = userDao.insertUser(user);
            if (result)
                // 2.保存权限表,删除掉所有权限,再插入
                if (userDao.deleteUserPermission(user.getId())) {
                    String permission = user.getPermission();
                    //同时拥有两个权限
                    if ("3".equals(permission)) {
                        user.setPermission("1");
                        if (userDao.insertUserPermission(user)) {
                            user.setPermission("2");
                            result = userDao.insertUserPermission(user);
                        }
                    } else
                        result = userDao.insertUserPermission(user);
                }
            if (result)
                commit(status);
            else
                rollback(status);
        } catch (Exception e) {
            rollback(status);
            throw e;
        }
        return result;
    }

    @Override
    public boolean deleteUser(String id) {
        User user = userDao.selectUserByID(id);
        if (user != null) {
            user.setStatus('1');
            return userDao.updateUser(user);
        }
        return false;
    }

}
