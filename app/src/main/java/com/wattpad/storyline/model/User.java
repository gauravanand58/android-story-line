package com.wattpad.storyline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    public User(){
        setToDefaults();
    }

    private void setToDefaults(){
        userName = "User Name";
        userAvatar = "User Avatar";
        userFullName = "User FullName";
    }

    @Expose
    @SerializedName("name")
    private String userName;

    @Expose
    @SerializedName("avatar")
    private String userAvatar;

    @Expose
    @SerializedName("fullname")
    private String userFullName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }
}
