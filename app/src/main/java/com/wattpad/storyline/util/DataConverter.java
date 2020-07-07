package com.wattpad.storyline.util;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wattpad.storyline.model.User;

import java.lang.reflect.Type;

public class DataConverter {

    @TypeConverter
    public String fromUser(User user) {
        if (user == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<User>() {
        }.getType();
        return gson.toJson(user, type);
    }

    @TypeConverter
    public User toUser(String userJson) {
        if (userJson == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<User>() {
        }.getType();
        return gson.fromJson(userJson, type);
    }
}