package com.wattpad.storyline.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wattpad.storyline.util.DataConverter;

@Entity(tableName = "story")
public class Story {

    @Expose
    @PrimaryKey
    private long id;

    @Expose
    private String title;

    @Expose
    @TypeConverters(DataConverter.class)
    private User user;

    @Expose
    @SerializedName("cover")
    private String coverImage;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}