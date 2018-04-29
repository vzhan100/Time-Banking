package com.timebanking.firebase;

public class Job {
    private String title, desc;
    private Long pay;
    private String userId;
    private String address;

    //Job class
    public Job(String title, String desc, Long pay, String address, String userId){
        this.title = title;
        this.desc = desc;
        this.pay = pay;
        this.userId = userId;
        this.address = address;
    }

    //Default constructor
    public Job(){}

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public Long getPay() {
        return pay;
    }

    public String getUserId() {
        return userId;
    }

    public String getAddress() {
        return address;
    }
}
