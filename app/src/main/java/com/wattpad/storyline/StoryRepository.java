package com.wattpad.storyline;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class StoryRepository {
    private static final String BASE_URL = "https://www.wattpad.com/api/v3/";
    private static final long FRESH_TIMEOUT = TimeUnit.HOURS.toMillis(1);
    private static StoryRepository sInstance;

    private Context context;
    private WebService webService;
    private StoryDao storyDao;
    private MutableLiveData<List<Story>> storyList;

    private StoryRepository(Application application) {
        final Gson gson = new GsonBuilder().setFieldNamingPolicy(
                FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        webService = retrofit.create(WebService.class);
        StoryRoomDatabase database = StoryRoomDatabase.getDatabase(application);
        storyDao = database.storyDao();
        context = application.getApplicationContext();
        storyList = new MutableLiveData<>();
        fetchStoriesFromWeb();
        fetchListFromDB();
    }

    public static StoryRepository getInstance(Application application) {
        if (sInstance == null) {
            sInstance = new StoryRepository(application);
        }
        return sInstance;
    }

    public LiveData<List<Story>> getStoryList(){
        return storyList;
    }

    public void fetchListFromDB(){
//        if(storyList.getValue() == null) {
//            fetchFromWeb();
//        }

        Maybe<List<Story>> single = storyDao.getStoryLine();
        single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Story>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // add it to a CompositeDisposable
                    }

                    @Override
                    public void onSuccess(List<Story> stories) {
                        Log.d("Gaurav:", "data fetched from db" + stories.toString());
                        if(stories.size() == 0) {
                            Log.d("Gaurav:", "data fetching from web");
                            fetchStoriesFromWeb();
                        } else{
                            storyList.setValue(stories);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Gaurav:", "error"+e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void fetchStoriesFromWeb() {
        Observable<StoryPage> list = webService.getStoryList();
        Log.d("Gaurav", list.toString());
        list.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoryPage>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("Gaurav", "subscribed");
                    }

                    @Override
                    public void onNext(StoryPage page) {
                        Log.d("Gaurav", "fetched items");
                        Log.d("Gaurav:", Arrays.toString(page.getStories().toArray()));
                        updateStoriesToDB(page.getStories());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Gaurav:", e.toString());
                        Log.d("Gaurav", "error while fetching");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("Gaurav", "fetching done");
                    }
                });
    }

    private void updateStoriesToDB(List<Story> stories){
        Completable insert = Completable.fromAction(() -> storyDao.insertAllOrders(stories));
        Completable delete = Completable.fromAction(() -> storyDao.deleteAll());

        delete.andThen(Completable.fromAction(() ->Log.d("Gaurav", "Delete finished")))
                .andThen(insert)
                .andThen(Completable.fromAction(() ->Log.d("Gaurav", "Insert finished")))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // just like with a Single
                    }

                    @Override
                    public void onComplete() {
                        storyList.setValue(stories);
                        Toast.makeText(context, "Stories saved for offline mode.", Toast.LENGTH_LONG).show();
                        Log.d("Gaurav:", "updated all");
                    }

                    @Override
                    public void onError(Throwable e) {
                        // something went wrong
                    }
                });


    }
}




