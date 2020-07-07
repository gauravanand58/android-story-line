package com.wattpad.storyline.model;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class StoryPageTest {
    @Test
    public void setStoriesTest(){
        StoryPage storyPage = new StoryPage();
        List<Story> storyList = new ArrayList<>();
        storyList.add(new Story());
        storyPage.setStories(storyList);
        assertEquals(1, storyPage.getStories().size());
    }

    @Test
    public void getStoriesTest(){
        StoryPage storyPage = new StoryPage();
        List<Story> storyList = new ArrayList<>();
        storyList.add(new Story());
        storyPage.setStories(storyList);
        assertEquals(1, storyPage.getStories().size());
    }

    @Test
    public void setNextUrlTest(){
        StoryPage storyPage = new StoryPage();
        storyPage.setNextUrl("Sample NextUrl");
        assertEquals("Sample NextUrl", storyPage.getNextUrl());
    }

    @Test
    public void getNextUrlTest(){
        StoryPage storyPage = new StoryPage();
        storyPage.setNextUrl("Sample NextUrl");
        assertEquals("Sample NextUrl", storyPage.getNextUrl());
    }
}
