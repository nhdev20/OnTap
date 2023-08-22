package com.techelevator.model;

public class DayHours {
    private String day;
    private boolean openStatus;
    private int startTime;
    private String startAmPm;
    private int endTime;
    private String endAmPm;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }


    public boolean isOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(boolean openStatus) {
        this.openStatus = openStatus;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public String getStartAmPm() {
        return startAmPm;
    }

    public void setStartAmPm(String startAmPm) {
        this.startAmPm = startAmPm;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getEndAmPm() {
        return endAmPm;
    }

    public void setEndAmPm(String endAmPm) {
        this.endAmPm = endAmPm;
    }
}
