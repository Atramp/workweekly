package com.teradata.workweekly.bean.entity;

/**
 * Created by alex on 15/10/21.
 */
public class UserPermission extends User {
    private String permission;
    private String startDate;

    public UserPermission() {

    }

    public UserPermission(User user) {
        setId(user.getId());
        setName(user.getName());
        setPassword(user.getPassword());
        setPhone(user.getPhone());
        setSupervisor(user.getSupervisor());
        setStatus(user.getStatus());
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
