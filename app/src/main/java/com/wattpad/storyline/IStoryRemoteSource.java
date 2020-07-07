package com.wattpad.storyline;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;

public interface IStoryRemoteSource {
    public Maybe<StoryPage> getStoryPageFromWeb();
}
