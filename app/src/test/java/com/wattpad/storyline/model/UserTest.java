package com.wattpad.storyline.model;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

public class UserTest {
    @Test
    public void setUserNameTest(){
        User user = new User();
        user.setUserName("Sample UserName");
        assertEquals("Sample UserName", user.getUserName());
    }

    @Test
    public void getUserNameTest(){
        User user = new User();
        user.setUserName("Sample UserName");
        assertEquals("Sample UserName", user.getUserName());
    }

    @Test
    public void setUserAvatarTest(){
        User user = new User();
        user.setUserAvatar("Sample UserAvatar");
        assertEquals("Sample UserAvatar", user.getUserAvatar());
    }

    @Test
    public void getUserAvatarTest(){
        User user = new User();
        user.setUserAvatar("Sample UserAvatar");
        assertEquals("Sample UserAvatar", user.getUserAvatar());
    }

    @Test
    public void setUserFullNameTest(){
        User user =new User();
        user.setUserFullName("Sample FullName");
        assertEquals("Sample FullName", user.getUserFullName());
    }

    @Test
    public void getUserFullNameTest(){
        User user =new User();
        user.setUserFullName("Sample FullName");
        assertEquals("Sample FullName", user.getUserFullName());
    }
}
