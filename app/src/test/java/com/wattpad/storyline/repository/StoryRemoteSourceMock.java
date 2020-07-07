package com.wattpad.storyline.repository;

import com.wattpad.storyline.data.IStoryRemoteSource;
import com.wattpad.storyline.model.StoryPage;

import io.reactivex.Maybe;

public class StoryRemoteSourceMock implements IStoryRemoteSource {
    @Override
    public Maybe<StoryPage> getStoryPageFromWeb() {
        StoryPage storyPage = new StoryPage();
        return Maybe.just(storyPage);
    }
}
