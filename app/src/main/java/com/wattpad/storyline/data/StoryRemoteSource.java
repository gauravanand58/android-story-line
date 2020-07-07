package com.wattpad.storyline.data;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wattpad.storyline.model.StoryPage;

import io.reactivex.Maybe;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class StoryRemoteSource implements IStoryRemoteSource {
    private static final String BASE_URL = "https://www.wattpad.com/api/v3/";
    private WebService webService;

    public StoryRemoteSource() {
        final Gson gson = new GsonBuilder().setFieldNamingPolicy(
                FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        webService = retrofit.create(WebService.class);
    }

    @Override
    public Maybe<StoryPage> getStoryPageFromWeb() {
        return webService.getStoryList();
    }
}
