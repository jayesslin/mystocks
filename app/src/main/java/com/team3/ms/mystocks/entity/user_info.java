package com.team3.ms.mystocks.entity;


public class user_info {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id ;

    private user_info(){

    }
    private final static user_info INSTANCE = new user_info();
    public static user_info getInstance(){

        return INSTANCE;
    }
}
