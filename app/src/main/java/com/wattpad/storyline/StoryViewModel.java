package com.wattpad.storyline;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StoryViewModel extends AndroidViewModel {
    private StoryRepository storyRepository;

    private LiveData<List<Story>> storyList;

    public StoryViewModel (Application application){
        super(application);
        storyRepository = StoryRepository.getInstance(application);
        storyList = storyRepository.getStoryList();
    }

    LiveData<List<Story>> getStoryList(){
        return storyList;
    }
}
