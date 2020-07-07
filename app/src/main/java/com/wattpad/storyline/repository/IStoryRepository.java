package com.wattpad.storyline.repository;

import androidx.lifecycle.MutableLiveData;

import com.wattpad.storyline.model.Story;

import java.util.List;

public interface IStoryRepository {
    MutableLiveData<List<Story>> fetchDataFromDB();
}
