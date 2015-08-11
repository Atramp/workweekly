package com.teradata.workweekly.bean.entity;

/**
 * Created by alex on 15/7/22.
 */
public class Requirement extends Entity {
    private String catagoryID;
    private String color;
    private String source;
    private String department;
    private String sponsor;
    private String yzAgent;
    private String handler;
    private String initalDate;
    private String expectedFinishDate;
    private String startDate;
    private String finishDate;
    private String feedback;
    private String description;

    public String getCatagoryID() {
        return catagoryID;
    }

    public void setCatagoryID(String catagoryID) {
        this.catagoryID = catagoryID;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getYzAgent() {
        return yzAgent;
    }

    public void setYzAgent(String yzAgent) {
        this.yzAgent = yzAgent;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getInitalDate() {
        return initalDate;
    }

    public void setInitalDate(String initalDate) {
        this.initalDate = initalDate;
    }

    public String getExpectedFinishDate() {
        return expectedFinishDate;
    }

    public void setExpectedFinishDate(String expectedFinishDate) {
        this.expectedFinishDate = expectedFinishDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
