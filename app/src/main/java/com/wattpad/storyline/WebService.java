package com.wattpad.storyline;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebService {
    @GET("stories?offset=0&limit=10&fields=stories(id,title,cover,user)&filter=new")
    Observable<StoryPage> getStoryList();
}
