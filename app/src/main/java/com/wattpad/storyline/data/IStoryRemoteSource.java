package com.wattpad.storyline.data;

import com.wattpad.storyline.model.StoryPage;

import io.reactivex.Maybe;

public interface IStoryRemoteSource {
    Maybe<StoryPage> getStoryPageFromWeb();
}
