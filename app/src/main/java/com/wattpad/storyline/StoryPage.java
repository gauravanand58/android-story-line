package com.wattpad.storyline;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StoryPage {
    @Expose
    @SerializedName("stories")
    private List<Story> stories;

    @Expose
    @SerializedName("nextUrl")
    private String nextUrl;

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }
}
