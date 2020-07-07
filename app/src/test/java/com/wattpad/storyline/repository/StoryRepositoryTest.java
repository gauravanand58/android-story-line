package com.wattpad.storyline.repository;

import static junit.framework.Assert.assertNotNull;

import com.wattpad.storyline.data.IStoryDao;
import com.wattpad.storyline.data.IStoryRemoteSource;

import org.junit.Test;

public class StoryRepositoryTest {
    @Test
    public void fetchDataFromDBTest(){
        IStoryDao storyDaoMock = new StoryDaoMock();
        assertNotNull(storyDaoMock.getStoryLine());
    }

    @Test
    public void fetchDataFromWebTest(){
        IStoryRemoteSource storyRemoteSourceMock = new StoryRemoteSourceMock();
        assertNotNull(storyRemoteSourceMock.getStoryPageFromWeb());
    }
}
