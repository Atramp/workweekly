package com.teradata.workweekly.bean.entity;

/**
 * Created by alex on 15/7/22.
 */
public class User extends Entity {
    private String password;
    private String phone;
    private String supervisor;
    private char status = '0';

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
