package com.wattpad.storyline.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.wattpad.storyline.repository.IStoryRepository;
import com.wattpad.storyline.model.Story;
import com.wattpad.storyline.repository.StoryRepository;

import java.util.List;

public class StoryViewModel extends AndroidViewModel {
    private IStoryRepository mStoryRepository;

    private LiveData<List<Story>> storyList;

    public StoryViewModel(Application application) {
        super(application);
        mStoryRepository = StoryRepository.getInstance(application);
        storyList = mStoryRepository.fetchDataFromDB();
    }

    public LiveData<List<Story>> getStoryList() {
        return storyList;
    }
}
