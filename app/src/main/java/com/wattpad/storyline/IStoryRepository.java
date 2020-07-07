package com.wattpad.storyline;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface IStoryRepository {
    public LiveData<List<Story>> getStoryList();
    public void fetchStoriesFromDB();
}
