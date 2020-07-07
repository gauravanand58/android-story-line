package com.wattpad.storyline.repository;

import com.wattpad.storyline.data.IStoryDao;
import com.wattpad.storyline.model.Story;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;

public class StoryDaoMock implements IStoryDao {
    private List<Story> list;

    public StoryDaoMock(){
        list = new ArrayList<>();
        list.add(new Story());
    }

    public List<Story> getList() {
        return list;
    }

    @Override
    public Maybe<List<Story>> getStoryLine() {
        return Maybe.just(list);
    }

    @Override
    public void deleteAll() {
        list.clear();
    }

    @Override
    public void insertAllStories(List<Story> stories) {
        list = stories;
    }
}
