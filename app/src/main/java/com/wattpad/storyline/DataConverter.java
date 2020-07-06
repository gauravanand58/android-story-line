package com.wattpad.storyline;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class DataConverter {

    @TypeConverter
    public String fromUser(User user) {
        if (user == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<User>() {}.getType();
        String json = gson.toJson(user, type);
        return json;
    }

    @TypeConverter
    public User toUser(String userJson) {
        if (userJson == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<User>() {}.getType();
        User user = gson.fromJson(userJson, type);
        return user;
    }
}
