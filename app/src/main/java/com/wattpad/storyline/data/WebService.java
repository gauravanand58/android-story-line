package com.wattpad.storyline.data;

import com.wattpad.storyline.model.StoryPage;

import io.reactivex.Maybe;
import retrofit2.http.GET;

public interface WebService {
    @GET("stories?offset=0&limit=10&fields=stories(id,title,cover,user)&filter=new")
    Maybe<StoryPage> getStoryList();
}
