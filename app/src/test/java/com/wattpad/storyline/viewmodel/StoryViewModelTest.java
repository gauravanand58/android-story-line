package com.wattpad.storyline.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;

import static junit.framework.Assert.assertEquals;

import com.wattpad.storyline.model.Story;
import com.wattpad.storyline.repository.IStoryRepository;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.List;

public class StoryViewModelTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Test
    public void getStoryListTest() {
        LiveData<List<Story>> storyList;
        IStoryRepository storyRepositoryMock = new StoryRepositoryMock();
        storyList = storyRepositoryMock.fetchDataFromDB();
        assertEquals(1, storyList.getValue().size());
    }
}
