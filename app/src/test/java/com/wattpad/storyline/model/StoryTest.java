package com.wattpad.storyline.model;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class StoryTest {
    @Test
    public void setIdTest(){
        Story story = new Story();
        story.setId(12345);
        assertEquals(12345, story.getId());
    }

    @Test
    public void getIdTest(){
        Story story = new Story();
        story.setId(12345);
        assertEquals(12345, story.getId());
    }

    @Test
    public void setTitleTest(){
        Story story = new Story();
        story.setTitle("Sample Title");
        assertEquals("Sample Title", story.getTitle());
    }

    @Test
    public void getTitleTest(){
        Story story = new Story();
        story.setTitle("Sample Title");
        assertEquals("Sample Title", story.getTitle());
    }

    @Test
    public void setCoverImageTest(){
        Story story = new Story();
        story.setCoverImage("Sample CoverImage");
        assertEquals("Sample CoverImage", story.getCoverImage());
    }

    @Test
    public void getCoverImageTest(){
        Story story = new Story();
        story.setCoverImage("Sample CoverImage");
        assertEquals("Sample CoverImage", story.getCoverImage());
    }

    @Test
    public void setUserTest(){
        Story story = new Story();
        story.setUser(new User());
        assertEquals("User Name", story.getUser().getUserName());
    }

    @Test
    public void getUserTest(){
        Story story = new Story();
        story.setUser(new User());
        assertEquals("User Name", story.getUser().getUserName());
    }
}
