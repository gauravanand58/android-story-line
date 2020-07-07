package com.wattpad.storyline.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.wattpad.storyline.model.Story;
import com.wattpad.storyline.repository.IStoryRepository;

import java.util.ArrayList;
import java.util.List;

public class StoryRepositoryMock implements IStoryRepository {

    @Override
    public MutableLiveData<List<Story>> fetchDataFromDB() {
        MutableLiveData<List<Story>> data = new MutableLiveData<>();
        List<Story> list = new ArrayList<>();
        list.add(new Story());
        data.setValue(list);
        return data;
    }
}
